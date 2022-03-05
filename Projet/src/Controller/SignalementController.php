<?php

namespace App\Controller;

use App\Entity\Signalement;
use App\Form\SignalementType;
use App\Repository\SignalementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/signalement")
 */
class SignalementController extends AbstractController
{
    /**
     * @Route("/", name="signalement_index", methods={"GET"})
     */
    public function index(SignalementRepository $signalementRepository): Response
    {
        return $this->render('signalement/index.html.twig', [
            'signalements' => $signalementRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="signalement_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $signalement = new Signalement();
        $form = $this->createForm(SignalementType::class, $signalement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($signalement);
            $entityManager->flush();

            return $this->redirectToRoute('signalement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('signalement/new.html.twig', [
            'signalement' => $signalement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="signalement_show", methods={"GET"})
     */
    public function show(Signalement $signalement): Response
    {
        return $this->render('signalement/show.html.twig', [
            'signalement' => $signalement,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="signalement_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Signalement $signalement): Response
    {
        $form = $this->createForm(SignalementType::class, $signalement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('signalement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('signalement/edit.html.twig', [
            'signalement' => $signalement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="signalement_delete", methods={"POST"})
     */
    public function delete(Request $request, Signalement $signalement): Response
    {
        if ($this->isCsrfTokenValid('delete'.$signalement->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($signalement);
            $entityManager->flush();
        }

        return $this->redirectToRoute('signalement_index', [], Response::HTTP_SEE_OTHER);
    }
}

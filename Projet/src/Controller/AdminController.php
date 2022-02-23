<?php

namespace App\Controller;

use App\Entity\User;
use App\Entity\Utilisateur;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class AdminController extends AbstractController
{
    /**
     * @Route("/admin", name="admin")
     */
    public function afficher()
    {
        $users = $this->getDoctrine()->getRepository(Utilisateur::class)->findAll();


        return $this->render('admin/administrations.html.twig', [
            'users' => $users
        ]);
    }



    /**
     * @Route("/ajoutuser", name="ajoutuser")
     */
    public function ajout(Request $request,UserPasswordEncoderInterface $passwordEncoder): Response
    {
        if($request->getMethod()=='POST') {
            $user = new Utilisateur();
            $user->setNom($request->get('nom'));
            $user->setEmail($request->get('email'));
            $user->setPrenom($request->get('prenom'));
            $user->setCin($request->get('cin'));
            $user->setSexe($request->get('sexe'));
            $user->setPassword($request->get('password'));
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();
            return $this->redirectToRoute('admin'
            );
        }
        return $this->render('admin/ajouteruser.html.twig'
        );
    }
    /**
     * @Route("/modifuser/{iduser}", name="modifuser")
     */
    public function modifier(Request $request, $iduser,UserPasswordEncoderInterface $passwordEncoder): Response
    {


        $user=$this->getDoctrine()->getRepository(Utilisateur::class)->find($iduser);


        if($request->getMethod()=='POST') {
            $user->setNom($request->get('nom'));
            $user->setPrenom($request->get('prenom'));
            $user->setCin($request->get('cin'));
            $user->setSexe($request->get('sexe'));

            $user->setPassword($request->get('password'));
            $user->setRoles($request->get('role'));
            $user->setEmail($request->get('email'));



            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->flush();
            return $this->redirectToRoute('admin'
            );
        }
        return $this->render('admin/modifieruser.html.twig',[
            'users'=>$user

        ]);
    }
    /**
     * @Route("/suppuser/{iduser}", name="suppuser")
     */

    public function supprimer(Request $request, $iduser) {
        $user=$this->getDoctrine()->getRepository(Utilisateur::class)->find($iduser);

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($user);
        $entityManager->flush();

        $response = new Response();
        $response->send();

        return $this->redirectToRoute('admin');
    }

}

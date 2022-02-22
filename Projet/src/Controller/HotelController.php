<?php

namespace App\Controller;

use App\Entity\Hotel;
use App\Form\HotelType;
use App\Repository\HotelRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HotelController extends AbstractController
{


    /**
     * @Route("/ajouterhotel",name="ajouterhotel")
     */
    public function addHotel(EntityManagerInterface $em,Request $request){
        $hotel= new Hotel();

        $form= $this->createForm(HotelType::class,$hotel);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $imageFile = $form->get('imghotel')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
                try {
                    $imageFile->move(
                        'back\img',
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                $hotel->setImghotel($newFilename);
            }

            $em->persist($hotel);
            $em->flush();

            return $this->redirectToRoute("afficherhotel");

        }
        return $this->render("hotel/ajouterhotel.html.twig",array("formulaire"=>$form->createView()));
    }

    /**
     * @Route("/afficherhotel",name="afficherhotel")
     */
    public function Affiche(HotelRepository $repository){
        $tablehotels=$repository->findAll();
        return $this->render('hotel/afficherhotel.html.twig'
            ,['tablehotels'=>$tablehotels]);

    }
    /**
     * @Route("/afficherhotelClient",name="afficherhotelClient")
     */
    public function afficherhotelClient(HotelRepository $repository){
        $tablehotels=$repository->findAll();
        return $this->render('hotel/AfficheHotelClient.html.twig'
            ,['tablehotels'=>$tablehotels]);

    }

    /**
     * @Route("/supprimerhotel/{id}",name="supprimerhotel")
     */
    public function supprimerHotel($id,EntityManagerInterface $em ,HotelRepository $repository){
        $hotel=$repository->find($id);
        $em->remove($hotel);
        $em->flush();

        return $this->redirectToRoute('afficherhotel');
    }

    /**
     * @Route("/{id}/modifierhotel", name="modifierhotel", methods={"GET","POST"})
     */
    public function edit(Request $request, Hotel $hotel): Response
    {
        $form = $this->createForm(HotelType::class, $hotel);
        $form->add('Confirmer',SubmitType::class);

        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('imghotel')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
                try {
                    $imageFile->move(
                        'back\img',
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                $hotel->setImghotel($newFilename);
            }
            $this->getDoctrine()->getManager()->flush();


            return $this->redirectToRoute('afficherhotel');
        }

        return $this->render('hotel/modifierhotel.html.twig', [
            'hotelmodif' => $hotel,
            'form' => $form->createView(),
        ]);
    }



    /**
     * @Route("/hotel/{id}",name="get_hotel_info")

     */

    public function getById (HotelRepository $repository, Request $request  )
    {

        $id = $request->get('id');

        $hotel = $repository->findOneBy(['id' => $id]);




        return $this->render("hotel/afficherdetailhotel.html.twig",['hotel' => $hotel]) ;

    }



















}
<?php

namespace GT\TournoiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('TournoiBundle:Default:index.html.twig');
    }
    
   public function showAction(Groupe $groupe)
    {
        $deleteForm = $this->createDeleteForm($groupe);

        return $this->render('TournoiBundle:groupe:index.html.twig', array(
            'groupe' => $groupe,
            'delete_form' => $deleteForm->createView(),
        ));
    }
    
    public function newAction(Request $request)
    {
        $groupe = new Groupe();
        
        $form = $this->createForm('GT\TournoiBundle\Form\GroupeType', $groupe);
        
        $form->handleRequest($request);

       if ($form->isSubmitted() && $form->isValid()) {
           //enregistrement dans la base de donnée
            $em = $this->getDoctrine()->getManager();
            $em->persist($groupe);
            $em->flush($groupe);

            return $this->redirectToRoute('groupes_show', array('id' => $groupe->getId()));
        }

        return $this->render('groupe/new.html.twig', array(
            'groupe' => $groupe,
            'form' => $form->createView(),
        ));
    }
}
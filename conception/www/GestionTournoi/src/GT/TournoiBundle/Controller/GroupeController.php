<?php

namespace GT\TournoiBundle\Controller;

use GT\TournoiBundle\Entity\Groupe;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Groupe controller.
 *
 */
class GroupeController extends Controller
{
    /**
     * Lists all groupe entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $groupes = $em->getRepository('TournoiBundle:Groupe')->findAll();

        return $this->render('groupe/index.html.twig', array(
            'groupes' => $groupes
        ));
    }

    /**
     * Creates a new groupe entity.
     *
     */
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

    /**
     * Finds and displays a groupe entity.
     *
     */
    public function showAction(Groupe $groupe)
    {
        $deleteForm = $this->createDeleteForm($groupe);

        return $this->render('groupe/show.html.twig', array(
            'groupe' => $groupe,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing groupe entity.
     *
     */
    public function editAction(Request $request, Groupe $groupe)
    {
        $deleteForm = $this->createDeleteForm($groupe);
        $editForm = $this->createForm('GT\TournoiBundle\Form\GroupeType', $groupe);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();
                      
            $request->getSession()
                ->getFlashBag()
                ->add('success', 'Groupe édité');
            
            return $this->redirectToRoute('accueil_index', array('id' => $groupe->getId()));
        }

        
        return $this->render('groupe/edit.html.twig', array(
            'groupe' => $groupe,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }
    
public function supprimerAction(Groupe $groupe)
    {
        $deleteForm = $this->createDeleteForm($groupe);

        return $this->render('groupe/supprimer.html.twig', array(
            'groupe' => $groupe,
            'delete_form' => $deleteForm->createView(),
        ));
    }

public function deleteAction(Request $request, Groupe $groupe)
    {
        $deleteform = $this->createDeleteForm($groupe);
        $deleteform->handleRequest($request);

        if ($deleteform->isSubmitted() && $deleteform->isValid()) {
            
            $request->getSession()
                ->getFlashBag()
                ->add('success', 'Groupe supprimé');
            
            $em = $this->getDoctrine()->getManager();
            $em->remove($groupe);
            $em->flush($groupe);
        }
        return $this->redirectToRoute('groupes_index');
    }
    
        /**
     * Creates a form to delete a equipe entity.
     *
     * @param Groupe $groupe The equipe entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Groupe $groupe)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('groupes_delete', array('id' => $groupe->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}

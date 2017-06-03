<?php

namespace GT\TournoiBundle\Controller;

use GT\TournoiBundle\Entity\Score;
use GT\TournoiBundle\Entity\Rencontre;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Rencontre controller.
 *
 */
class RencontreController extends Controller
{
    /**
     * Lists all rencontre entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $rencontres = $em->getRepository('TournoiBundle:Rencontre')->findAll();

        return $this->render('rencontre/index.html.twig', array(
            'rencontres' => $rencontres,
        ));
    }

        /**
     * Lists all rencontre entities.
     *
     */
    public function planningAction()
    {
        $em = $this->getDoctrine()->getManager();

        $rencontres = $em->getRepository('TournoiBundle:Rencontre')->findAll();

        return $this->render('rencontre/planning.html.twig',array(
            'rencontres' => $rencontres,
                ));
    }

    /**
     * Creates a new rencontre entity.
     *
     */
    public function newAction(Request $request)
    {
        $rencontre = new Rencontre();
        $form = $this->createForm('GT\TournoiBundle\Form\RencontreType', $rencontre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($rencontre);
            $em->flush($rencontre);
                
            //Créer nouveau score
        }

        return $this->render('rencontre/new.html.twig', array(
            'rencontre' => $rencontre,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a rencontre entity.
     *
     */
    public function showAction(Rencontre $rencontre)
    {
        $deleteForm = $this->createDeleteForm($rencontre);

        return $this->render('rencontre/planning.html.twig', array(
            'rencontre' => $rencontre,
            'delete_form' => $deleteForm->createView(),
        ));
        
    }

    /**
     * Displays a form to edit an existing rencontre entity.
     *
     */
    public function editAction(Request $request, Rencontre $rencontre)
    {
        $deleteForm = $this->createDeleteForm($rencontre);
        $editForm = $this->createForm('GT\TournoiBundle\Form\RencontreType', $rencontre);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('rencontres_edit', array('id' => $rencontre->getId()));
        }

        return $this->render('rencontre/edit.html.twig', array(
            'rencontre' => $rencontre,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }
    
    public function supprimerAction(Rencontre $rencontre)
    {
        $deleteForm = $this->createDeleteForm($rencontre);

        return $this->render('rencontre/supprimer.html.twig', array(
            'rencontre' => $rencontre,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a rencontre entity.
     *
     */
    public function deleteAction(Request $request, Rencontre $rencontre)
    {
        $form = $this->createDeleteForm($rencontre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            
            $request->getSession()
                ->getFlashBag()
                ->add('success', 'Rencontre supprimé');
            
            $em = $this->getDoctrine()->getManager();
            $em->remove($rencontre);
            $em->flush($rencontre);
        }

        return $this->redirectToRoute('rencontres_planning');
    }

    /**
     * Creates a form to delete a rencontre entity.
     *
     * @param Rencontre $rencontre The rencontre entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Rencontre $rencontre)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('rencontres_delete', array('id' => $rencontre->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}

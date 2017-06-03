<?php

namespace GT\TournoiBundle\Controller;

use GT\TournoiBundle\Entity\Phase;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Phase controller.
 *
 */
class PhaseController extends Controller
{
    /**
     * Lists all phase entities.
     *
     */
    
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $phases = $em->getRepository('TournoiBundle:Phase')->findAll();

        return $this->render('phase/index.html.twig', array(
            'phases' => $phases,
        ));
    }
    
    public function groupeAction()
    {
        $em = $this->getDoctrine()->getManager();

        $phases = $em->getRepository('TournoiBundle:Phase')->findAll();

        return $this->render('phase/groupe.html.twig', array(
            'phases' => $phases,
        ));
    }
    
    public function consolanteAction()
    {
        $em = $this->getDoctrine()->getManager();

        $phases = $em->getRepository('TournoiBundle:Phase')->findAll();

        return $this->render('phase/consolante.html.twig', array(
            'phases' => $phases,
        ));
    }
    
    public function principaleAction()
    {
        $em = $this->getDoctrine()->getManager();

        $phases = $em->getRepository('TournoiBundle:Phase')->findAll();

        return $this->render('phase/principale.html.twig', array(
            'phases' => $phases,
        ));
    }

    /**
     * Creates a new phase entity.
     *
     */
    public function newAction(Request $request)
    {
        $phase = new Phase();
        $form = $this->createForm('GT\TournoiBundle\Form\PhaseType', $phase);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($phase);
            $em->flush($phase);

            return $this->redirectToRoute('Phases_show', array('id' => $phase->getId()));
        }

        return $this->render('phase/new.html.twig', array(
            'phase' => $phase,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a phase entity.
     *
     */
    public function showAction(Phase $phase)
    {
        $deleteForm = $this->createDeleteForm($phase);

        return $this->render('phase/show.html.twig', array(
            'phase' => $phase,
            'delete_form' => $deleteForm->createView(),
        ));
    }
    

    /**
     * Displays a form to edit an existing phase entity.
     *
     */
    public function editAction(Request $request, Phase $phase)
    {
        $deleteForm = $this->createDeleteForm($phase);
        $editForm = $this->createForm('GT\TournoiBundle\Form\PhaseType', $phase);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('Phases_edit', array('id' => $phase->getId()));
        }

        return $this->render('phase/edit.html.twig', array(
            'phase' => $phase,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a phase entity.
     *
     */
    public function deleteAction(Request $request, Phase $phase)
    {
        $form = $this->createDeleteForm($phase);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($phase);
            $em->flush($phase);
        }

        return $this->redirectToRoute('Phases_index');
    }

    /**
     * Creates a form to delete a phase entity.
     *
     * @param Phase $phase The phase entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Phase $phase)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('Phases_delete', array('id' => $phase->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}

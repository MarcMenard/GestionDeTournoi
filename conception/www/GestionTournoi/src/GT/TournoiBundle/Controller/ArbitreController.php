<?php

namespace GT\TournoiBundle\Controller;

use GT\TournoiBundle\Entity\Arbitre;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Arbitre controller.
 *
 */
class ArbitreController extends Controller
{
    /**
     * Lists all arbitre entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $arbitres = $em->getRepository('TournoiBundle:Arbitre')->findAll();

        return $this->render('arbitre/index.html.twig', array(
            'arbitres' => $arbitres,
        ));
    }

    /**
     * Creates a new arbitre entity.
     *
     */
    public function newAction(Request $request)
    {
        $arbitre = new Arbitre();
        $form = $this->createForm('GT\TournoiBundle\Form\ArbitreType', $arbitre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($arbitre);
            $em->flush($arbitre);

            return $this->redirectToRoute('arbitres_show', array('id' => $arbitre->getId()));
        }

        return $this->render('arbitre/new.html.twig', array(
            'arbitre' => $arbitre,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a arbitre entity.
     *
     */
    public function showAction(Arbitre $arbitre)
    {
        $deleteForm = $this->createDeleteForm($arbitre);

        return $this->render('arbitre/show.html.twig', array(
            'arbitre' => $arbitre,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing arbitre entity.
     *
     */
    public function editAction(Request $request, Arbitre $arbitre)
    {
        $deleteForm = $this->createDeleteForm($arbitre);
        $editForm = $this->createForm('GT\TournoiBundle\Form\ArbitreType', $arbitre);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('arbitres_edit', array('id' => $arbitre->getId()));
        }

        return $this->render('arbitre/edit.html.twig', array(
            'arbitre' => $arbitre,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a arbitre entity.
     *
     */
    public function deleteAction(Request $request, Arbitre $arbitre)
    {
        $form = $this->createDeleteForm($arbitre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($arbitre);
            $em->flush($arbitre);
        }

        return $this->redirectToRoute('arbitres_index');
    }

    /**
     * Creates a form to delete a arbitre entity.
     *
     * @param Arbitre $arbitre The arbitre entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Arbitre $arbitre)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('arbitres_delete', array('id' => $arbitre->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}

<?php

namespace GT\TournoiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

/**
 * Arbitre controller.
 *
 */
class FemmeController extends Controller
{
    public function indexAction()
    {
        return $this->render('TournoiBundle:Default:index2.html.twig');
    }
 
}

<?php

namespace GT\TournoiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

/**
 * Arbitre controller.
 *
 */
class HommeController extends Controller
{
    public function indexAction()
    {
        return $this->render('TournoiBundle:Default:index.html.twig');
    }
 
}

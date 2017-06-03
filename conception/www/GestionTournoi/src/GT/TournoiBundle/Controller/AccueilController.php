<?php

namespace GT\TournoiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

/**
 * Arbitre controller.
 *
 */
class AccueilController extends Controller
{
    public function indexAction()
    {
        return $this->render('accueil/index.html.twig');
    }
 
}

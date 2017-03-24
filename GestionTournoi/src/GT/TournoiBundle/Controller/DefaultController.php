<?php

namespace GT\TournoiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('TournoiBundle:Default:index.html.twig');
    }
}

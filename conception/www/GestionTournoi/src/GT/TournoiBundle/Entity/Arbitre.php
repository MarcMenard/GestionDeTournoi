<?php

namespace GT\TournoiBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Arbitre
 *
 * @ORM\Table(name="arbitre")
 * @ORM\Entity(repositoryClass="GT\TournoiBundle\Repository\ArbitreRepository")
 */
class Arbitre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=45)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="motdepasse", type="string", length=255)
     */
    private $motdepasse;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Arbitre
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set motdepasse
     *
     * @param string $motdepasse
     *
     * @return Arbitre
     */
    public function setMotdepasse($motdepasse)
    {
        $this->motdepasse = $motdepasse;

        return $this;
    }

    /**
     * Get motdepasse
     *
     * @return string
     */
    public function getMotdepasse()
    {
        return $this->motdepasse;
    }
    
           /**
     * toString
     * @return string
     */
    public function __toString() 
    {
        return $this->getNom();
    }
}

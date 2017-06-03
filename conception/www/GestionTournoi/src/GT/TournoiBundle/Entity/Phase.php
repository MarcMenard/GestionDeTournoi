<?php

namespace GT\TournoiBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Phase
 *
 * @ORM\Table(name="phase")
 * @ORM\Entity(repositoryClass="GT\TournoiBundle\Repository\PhaseRepository")
 */
class Phase
{
    /**
     * @ORM\OneToMany(targetEntity="GT\TournoiBundle\Entity\Groupe", mappedBy="phase", cascade={"persist"})
     */
    private $groupes;
    
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
     * @return Phase
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
     * Add groupe
     *
     * @param \GT\TournoiBundle\Entity\Groupe $groupe
     *
     * @return Phase
     */
    public function addGroupe(\GT\TournoiBundle\Entity\Groupe $groupe)
    {
        $this->groupes[] = $groupe;

        return $this;
    }

    /**
     * Remove groupe
     *
     * @param \GT\TournoiBundle\Entity\Groupe $groupe
     */
    public function removeGroupe(\GT\TournoiBundle\Entity\Groupe $groupe)
    {
        $this->groupes->removeElement($groupe);
    }

    /**
     * Get groupes
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getGroupes()
    {
        return $this->groupes;
    }

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->groupes = new \Doctrine\Common\Collections\ArrayCollection();
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

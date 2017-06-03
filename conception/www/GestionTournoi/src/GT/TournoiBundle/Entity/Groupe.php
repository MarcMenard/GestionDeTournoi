<?php

namespace GT\TournoiBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Groupe
 *
 * @ORM\Table(name="groupe")
 * @ORM\Entity(repositoryClass="GT\TournoiBundle\Repository\GroupeRepository")
 */
class Groupe {

    /**
     * @ORM\ManyToOne(targetEntity="GT\TournoiBundle\Entity\Phase", inversedBy="groupes")
     */
    private $phase;

    /**
     * @ORM\ManyToMany(targetEntity="GT\TournoiBundle\Entity\Equipe", cascade={"persist"})
     */
    private $equipes;

    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\OneToMany(targetEntity="GT\TournoiBundle\Entity\Rencontre", mappedBy="groupe")
     * @ORM\JoinColumn(nullable=false)
     */
    private $Rencontres;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=45)
     */
    private $nom;

    /**
     * @var int
     *
     * @ORM\Column(name="numero", type="integer")
     */
    private $numero;

    /**
     * Constructor
     */
    public function __construct()
    {
            $this->equipes = new \Doctrine\Common\Collections\ArrayCollection();
        $this->Rencontres = new \Doctrine\Common\Collections\ArrayCollection();
    }

    /**
     * Get id
     *
     * @return integer
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
     * @return Groupe
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
     * Set numero
     *
     * @param integer $numero
     *
     * @return Groupe
     */
    public function setNumero($numero)
    {
        $this->numero = $numero;

        return $this;
    }

    /**
     * Get numero
     *
     * @return integer
     */
    public function getNumero()
    {
        return $this->numero;
    }

    /**
     * Set phase
     *
     * @param \GT\TournoiBundle\Entity\Phase $phase
     *
     * @return Groupe
     */
    public function setPhase(\GT\TournoiBundle\Entity\Phase $phase = null)
    {
        $this->phase = $phase;

        return $this;
    }

    /**
     * Get phase
     *
     * @return \GT\TournoiBundle\Entity\Phase
     */
    public function getPhase()
    {
        return $this->phase;
    }

    /**
     * Add equipe
     *
     * @param \GT\TournoiBundle\Entity\Equipe $equipe
     *
     * @return Groupe
     */
    public function addEquipe(\GT\TournoiBundle\Entity\Equipe $equipe)
    {
        $this->equipes[] = $equipe;

        return $this;
    }

    /**
     * Remove equipe
     *
     * @param \GT\TournoiBundle\Entity\Equipe $equipe
     */
    public function removeEquipe(\GT\TournoiBundle\Entity\Equipe $equipe)
    {
        $this->equipes->removeElement($equipe);
    }

    /**
     * Get equipes
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getEquipes()
    {
        return $this->equipes;
    }

    /**
     * Add rencontre
     *
     * @param \GT\TournoiBundle\Entity\Rencontre $rencontre
     *
     * @return Groupe
     */
    public function addRencontre(\GT\TournoiBundle\Entity\Rencontre $rencontre)
    {
        $this->Rencontres[] = $rencontre;

        return $this;
    }

    /**
     * Remove rencontre
     *
     * @param \GT\TournoiBundle\Entity\Rencontre $rencontre
     */
    public function removeRencontre(\GT\TournoiBundle\Entity\Rencontre $rencontre)
    {
        $this->Rencontres->removeElement($rencontre);
    }

    /**
     * Get rencontres
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getRencontres()
    {
        return $this->Rencontres;
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

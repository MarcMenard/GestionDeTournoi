<?php

namespace GT\TournoiBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Groupe
 *
 * @ORM\Table(name="groupe")
 * @ORM\Entity(repositoryClass="GT\TournoiBundle\Repository\GroupeRepository")
 */
class Groupe
{
    
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
     * @ORM\ManyToOne(targetEntity="GT\TournoiBundle\Entity\Rencontre")
     * @ORM\JoinColumn(nullable=false)
     */
    private $Rencontre;

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
     * @return int
     */
    public function getNumero()
    {
        return $this->numero;
    }


    /**
     * Set rencontre
     *
     * @param \GT\TournoiBundle\Entity\Advert $rencontre
     *
     * @return Groupe
     */
    public function setRencontre(\GT\TournoiBundle\Entity\Advert $rencontre)
    {
        $this->Rencontre = $rencontre;

        return $this;
    }

    /**
     * Get rencontre
     *
     * @return \GT\TournoiBundle\Entity\Advert
     */
    public function getRencontre()
    {
        return $this->Rencontre;
    }
    /**
     * Constructor
     */
    public function __construct()
    {
        $this->groupe = new \Doctrine\Common\Collections\ArrayCollection();
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
        $this->equipe[] = $equipe;

        return $this;
    }

    /**
     * Remove equipe
     *
     * @param \GT\TournoiBundle\Entity\Equipe $equipe
     */
    public function removeEquipe(\GT\TournoiBundle\Entity\Equipe $equipe)
    {
        $this->equipe->removeElement($equipe);
    }

    /**
     * Get equipe
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getEquipe()
    {
        return $this->equipe;
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
}

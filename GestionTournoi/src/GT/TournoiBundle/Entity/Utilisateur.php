<?php

namespace GT\TournoiBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Utilisateur
 *
 * @ORM\Table(name="utilisateur")
 * @ORM\Entity(repositoryClass="GT\TournoiBundle\Repository\UtilisateurRepository")
 */
class Utilisateur
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
     * @var string
     *
     * @ORM\Column(name="mot_de_passe", type="string", length=45)
     */
    private $motDePasse;


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
     * @return Utilisateur
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
     * Set motDePasse
     *
     * @param string $motDePasse
     *
     * @return Utilisateur
     */
    public function setMotDePasse($motDePasse)
    {
        $this->motDePasse = $motDePasse;

        return $this;
    }

    /**
     * Get motDePasse
     *
     * @return string
     */
    public function getMotDePasse()
    {
        return $this->motDePasse;
    }



    /**
     * Set rencontre
     *
     * @param \GT\TournoiBundle\Entity\Rencontre $rencontre
     *
     * @return Utilisateur
     */
    public function setRencontre(\GT\TournoiBundle\Entity\Rencontre $rencontre)
    {
        $this->Rencontre = $rencontre;

        return $this;
    }

    /**
     * Get rencontre
     *
     * @return \GT\TournoiBundle\Entity\Rencontre
     */
    public function getRencontre()
    {
        return $this->Rencontre;
    }
}

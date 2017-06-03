<?php

namespace GT\TournoiBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Rencontre
 *
 * @ORM\Table(name="rencontre")
 * @ORM\Entity(repositoryClass="GT\TournoiBundle\Repository\RencontreRepository")
 */
class Rencontre
{
 
    /**
     * @ORM\ManyToOne(targetEntity="GT\TournoiBundle\Entity\Score", cascade={"persist"})
     */
    private $score1;
    
    /**
     * @ORM\ManyToOne(targetEntity="GT\TournoiBundle\Entity\Score", cascade={"persist"})
     */
    private $score2;
    
    /**
     * @var boolean
     *
     * @ORM\Column(name="jouee", type="boolean", options={"default":false})
     */
    private $jouee;
    
    /**
     * @ORM\ManyToOne(targetEntity="GT\TournoiBundle\Entity\Groupe", inversedBy="rencontres")
     * @ORM\JoinColumn(nullable=false)
     */
    private $groupe;
  
    /**
     * @ORM\ManyToOne(targetEntity="GT\TournoiBundle\Entity\Arbitre")
     * @ORM\JoinColumn(nullable=false)
     */
    private $arbitre;

  
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
     * @ORM\Column(name="terrain", type="string", length=45)
     */
    private $terrain;

    /**
     * @var int
     *
     * @ORM\Column(name="rang_planning", type="integer")
     */
    private $rangPlanning;

    /**
     * @var \Time
     *
     * @ORM\Column(name="heure", type="time")
     */
    private $heure;

    /**
     * @var int
     *
     * @ORM\Column(name="type_match", type="integer")
     */
    private $typeMatch;



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
     * Set terrain
     *
     * @param string $terrain
     *
     * @return Rencontre
     */
    public function setTerrain($terrain)
    {
        $this->terrain = $terrain;

        return $this;
    }

    /**
     * Get terrain
     *
     * @return string
     */
    public function getTerrain()
    {
        return $this->terrain;
    }

    /**
     * Set rangPlanning
     *
     * @param integer $rangPlanning
     *
     * @return Rencontre
     */
    public function setRangPlanning($rangPlanning)
    {
        $this->rangPlanning = $rangPlanning;

        return $this;
    }

    /**
     * Get rangPlanning
     *
     * @return int
     */
    public function getRangPlanning()
    {
        return $this->rangPlanning;
    }

    /**
     * Set heure
     *
     * @param \Time $heure
     *
     * @return Rencontre
     */
    public function setHeure($heure)
    {
        $this->heure = $heure;

        return $this;
    }

    /**
     * Get heure
     *
     * @return \Time
     */
    public function getHeure()
    {
        return $this->heure;
    }

    /**
     * Set typeMatch
     *
     * @param integer $typeMatch
     *
     * @return Rencontre
     */
    public function setTypeMatch($typeMatch)
    {
        $this->typeMatch = $typeMatch;

        return $this;
    }

    /**
     * Get typeMatch
     *
     * @return int
     */
    public function getTypeMatch()
    {
        return $this->typeMatch;
    }

   

    /**
     * Set groupe
     *
     * @param \GT\TournoiBundle\Entity\Groupe $groupe
     *
     * @return Rencontre
     */
    public function setGroupe(\GT\TournoiBundle\Entity\Groupe $groupe)
    {
        $this->groupe = $groupe;

        return $this;
    }

    /**
     * Set score1
     *
     * @param \GT\TournoiBundle\Entity\Score $score1
     *
     * @return Rencontre
     */
    public function setScore1(\GT\TournoiBundle\Entity\Score $score1 = null)
    {
        $this->score1 = $score1;

        return $this;
    }

    /**
     * Get score1
     *
     * @return \GT\TournoiBundle\Entity\Score
     */
    public function getScore1()
    {
        return $this->score1;
    }

    /**
     * Set score2
     *
     * @param \GT\TournoiBundle\Entity\Score $score2
     *
     * @return Rencontre
     */
    public function setScore2(\GT\TournoiBundle\Entity\Score $score2 = null)
    {
        $this->score2 = $score2;

        return $this;
    }

    /**
     * Get score2
     *
     * @return \GT\TournoiBundle\Entity\Score
     */
    public function getScore2()
    {
        return $this->score2;
    }

    /**
     * Set arbitre
     *
     * @param \GT\TournoiBundle\Entity\Arbitre $arbitre
     *
     * @return Rencontre
     */
    public function setArbitre(\GT\TournoiBundle\Entity\Arbitre $arbitre)
    {
        $this->arbitre = $arbitre;

        return $this;
    }

    /**
     * Get arbitre
     *
     * @return \GT\TournoiBundle\Entity\Arbitre
     */
    public function getArbitre()
    {
        return $this->arbitre;
    }

    /**
     * Get groupe
     *
     * @return \GT\TournoiBundle\Entity\Groupe
     */
    public function getGroupe()
    {
        return $this->groupe;
    }
    
           /**
     * toString
     * @return string
     */
    public function __toString() 
    {
        return $this->getNom();
    }

    /**
     * Set jouee
     *
     * @param boolean $jouee
     *
     * @return Rencontre
     */
    public function setJouee($jouee)
    {
        $this->jouee = $jouee;

        return $this;
    }

    /**
     * Get jouee
     *
     * @return boolean
     */
    public function getJouee()
    {
        return $this->jouee;
    }
}

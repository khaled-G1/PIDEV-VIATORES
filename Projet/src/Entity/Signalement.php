<?php

namespace App\Entity;

use App\Repository\SignalementRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=SignalementRepository::class)
 */
class Signalement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=Blogueur::class, inversedBy="signalements")
     */
    private $blogueur;

    /**
     * @ORM\ManyToOne(targetEntity=Opinion::class, inversedBy="signalements")
     */
    private $opinion;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getBlogueur(): ?Blogueur
    {
        return $this->blogueur;
    }

    public function setBlogueur(?Blogueur $blogueur): self
    {
        $this->blogueur = $blogueur;

        return $this;
    }

    public function getOpinion(): ?Opinion
    {
        return $this->opinion;
    }

    public function setOpinion(?Opinion $opinion): self
    {
        $this->opinion = $opinion;

        return $this;
    }
}

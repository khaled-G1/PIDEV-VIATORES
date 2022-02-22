<?php

namespace App\Entity;

use App\Repository\PromotionRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PromotionRepository::class)
 */
class Promotion
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     */
    private ?\DateTimeInterface $dateDebut;

    /**
     * @ORM\Column(type="date")
     */
    private ?\DateTimeInterface $dateFin;

    /**
     * @ORM\Column(type="integer")
     */
    private ?int $pourcentage;

    /**
     * @ORM\ManyToOne(targetEntity=Event::class, inversedBy="promo")
     * @ORM\JoinColumn(nullable=false)
     */
    private ArrayCollection $event;



    public function __construct()
    {
        $this->event = new ArrayCollection();
        $this->dateDebut = new \DateTime('now');
    }


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->dateDebut;
    }

    public function setDateDebut(\DateTimeInterface $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->dateFin;
    }

    public function setDateFin(\DateTimeInterface $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    public function getPourcentage(): ?int
    {
        return $this->pourcentage;
    }

    public function setPourcentage(int $pourcentage): self
    {
        $this->pourcentage = $pourcentage;

        return $this;
    }

    public function getEvent(): ArrayCollection
    {
        return $this->event;
    }

    public function setEvent(?Event $event): self
    {
        // unset the owning side of the relation if necessary
        if ($event === null && $this->event !== null) {
            $this->event->setPromo(null);
        }

        // set the owning side of the relation if necessary
        if ($event !== null && $event->getPromo() !== $this) {
            $event->setPromo($this);
        }

        $this->event = $event;

        return $this;
    }

    public function addEvent(Event $event): self
    {
        if (!$this->event->contains($event)) {
            $this->event[] = $event;
            $event->setPromotion($this);
        }

        return $this;
    }

    public function removeEvent(Event $event): self
    {
        if ($this->event->removeElement($event)) {
            // set the owning side to null (unless already changed)
            if ($event->getPromotion() === $this) {
                $event->setPromotion(null);
            }
        }

        return $this;
    }
}

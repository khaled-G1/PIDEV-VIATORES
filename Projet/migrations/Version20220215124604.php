<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220215124604 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67D82EA2E54');
        $this->addSql('CREATE TABLE chambre (id INT AUTO_INCREMENT NOT NULL, hotel_id INT DEFAULT NULL, type VARCHAR(255) NOT NULL, nb_lits VARCHAR(255) NOT NULL, etage INT NOT NULL, prix INT NOT NULL, vue VARCHAR(255) NOT NULL, INDEX IDX_C509E4FF3243BB18 (hotel_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE hotel (id INT AUTO_INCREMENT NOT NULL, ville VARCHAR(255) NOT NULL, nom VARCHAR(255) NOT NULL, nb_chambre INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE chambre ADD CONSTRAINT FK_C509E4FF3243BB18 FOREIGN KEY (hotel_id) REFERENCES hotel (id)');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE produit');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE chambre DROP FOREIGN KEY FK_C509E4FF3243BB18');
        $this->addSql('CREATE TABLE commande (id INT AUTO_INCREMENT NOT NULL, commande_id INT DEFAULT NULL, UNIQUE INDEX UNIQ_6EEAA67D82EA2E54 (commande_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE produit (id INT AUTO_INCREMENT NOT NULL, type_produit VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, prix_produit VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, date_expo DATE NOT NULL, date_fin DATE NOT NULL, disponibilit TINYINT(1) NOT NULL, type_action VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, imgproduit VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67D82EA2E54 FOREIGN KEY (commande_id) REFERENCES produit (id)');
        $this->addSql('DROP TABLE chambre');
        $this->addSql('DROP TABLE hotel');
    }
}

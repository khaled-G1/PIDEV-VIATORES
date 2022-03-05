<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220305173353 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('ALTER TABLE article CHANGE pourcentage_like pourcentage_like INT DEFAULT NULL');
        $this->addSql('ALTER TABLE `like` CHANGE article_id article_id INT DEFAULT NULL, CHANGE blogueur_id blogueur_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE opinion ADD nbr_signalement INT NOT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('ALTER TABLE article CHANGE pourcentage_like pourcentage_like INT DEFAULT NULL');
        $this->addSql('ALTER TABLE `like` CHANGE article_id article_id INT DEFAULT NULL, CHANGE blogueur_id blogueur_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE opinion DROP nbr_signalement');
    }
}

<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220222175614 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE reclammation ADD reponse_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reclammation ADD CONSTRAINT FK_1F8C1D97CF18BB82 FOREIGN KEY (reponse_id) REFERENCES reponse (id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_1F8C1D97CF18BB82 ON reclammation (reponse_id)');
        $this->addSql('ALTER TABLE reponse CHANGE id_reclamation reclamation_id INT NOT NULL');
        $this->addSql('ALTER TABLE reponse ADD CONSTRAINT FK_5FB6DEC72D6BA2D9 FOREIGN KEY (reclamation_id) REFERENCES reclammation (id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_5FB6DEC72D6BA2D9 ON reponse (reclamation_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE reclammation DROP FOREIGN KEY FK_1F8C1D97CF18BB82');
        $this->addSql('DROP INDEX UNIQ_1F8C1D97CF18BB82 ON reclammation');
        $this->addSql('ALTER TABLE reclammation DROP reponse_id');
        $this->addSql('ALTER TABLE reponse DROP FOREIGN KEY FK_5FB6DEC72D6BA2D9');
        $this->addSql('DROP INDEX UNIQ_5FB6DEC72D6BA2D9 ON reponse');
        $this->addSql('ALTER TABLE reponse CHANGE reclamation_id id_reclamation INT NOT NULL');
    }
}

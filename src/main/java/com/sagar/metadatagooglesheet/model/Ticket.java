package com.sagar.metadatagooglesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String createdBy;
    private LocalDate createdOn;
    @OneToOne
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToMany
    @JoinTable(
            name="developer_tickets",
            joinColumns = @JoinColumn(name="ticket_id"),
            inverseJoinColumns = @JoinColumn(name="developer_id")
    )
    private List<Developer> developers;

    public Ticket(String title, String description, String createdBy, LocalDate createdOn) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public Project getProject() {
        return this.project;
    }

    public List<Developer> getDevelopers() {
        return this.developers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Ticket)) return false;
        final Ticket other = (Ticket) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$createdBy = this.getCreatedBy();
        final Object other$createdBy = other.getCreatedBy();
        if (this$createdBy == null ? other$createdBy != null : !this$createdBy.equals(other$createdBy)) return false;
        final Object this$createdOn = this.getCreatedOn();
        final Object other$createdOn = other.getCreatedOn();
        if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false;
        final Object this$project = this.getProject();
        final Object other$project = other.getProject();
        if (this$project == null ? other$project != null : !this$project.equals(other$project)) return false;
        final Object this$developers = this.getDevelopers();
        final Object other$developers = other.getDevelopers();
        if (this$developers == null ? other$developers != null : !this$developers.equals(other$developers))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ticket;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $createdBy = this.getCreatedBy();
        result = result * PRIME + ($createdBy == null ? 43 : $createdBy.hashCode());
        final Object $createdOn = this.getCreatedOn();
        result = result * PRIME + ($createdOn == null ? 43 : $createdOn.hashCode());
        final Object $project = this.getProject();
        result = result * PRIME + ($project == null ? 43 : $project.hashCode());
        final Object $developers = this.getDevelopers();
        result = result * PRIME + ($developers == null ? 43 : $developers.hashCode());
        return result;
    }

    public String toString() {
        return "Ticket(id=" + this.getId() + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", createdBy=" + this.getCreatedBy() + ", createdOn=" + this.getCreatedOn()+ ")";
    }
}

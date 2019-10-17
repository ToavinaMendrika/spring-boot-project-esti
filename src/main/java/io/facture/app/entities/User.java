package io.facture.app.entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "le champ nom est requis")
    private String name;

    @NotBlank(message = "le champ email est requis")
    private String email;

    @NotBlank(message = "le champ mot de passe est requis")
    private String password;

    @NotBlank(message = "le champ status est requis")
    private String status;

    private  String ceo;

    @NotBlank(message = "le champ capital est requis")
    private String capital;

    @NotBlank(message = "le champ siège social est requis")
    private String hq;

    private String activity;
    private Date created_at;

    @ManyToOne
    @JoinColumn(name="roles_id")
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getHq() {
        return hq;
    }

    public void setHq(String hq) {
        this.hq = hq;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

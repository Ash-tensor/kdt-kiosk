package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;

    @Column(nullable = false)
    private String categoryName;

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}

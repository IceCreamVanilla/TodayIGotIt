package ldt.springframework.springmvc.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/*
 * author: Luu Duc Trung
 * https://github.com/luuductrung1234
 * ---
 * 7/22/18
 */

@Entity
public class OrderDetails extends AbstractDomainEntity{

    // =======================================
    // =          Attribute Section          =
    // =======================================

    private Integer quantity;

    @ManyToOne
    private Order order;

    @OneToOne
    private Course course;


    // =======================================
    // =        Constructors Section         =
    // =======================================

    public OrderDetails(){
    }

    public OrderDetails(Integer quantity, Order order, Course course){
        this.quantity = quantity;
        this.order = order;
        this.course = course;
    }


    // =======================================
    // =         Getters & Setters           =
    // =======================================

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
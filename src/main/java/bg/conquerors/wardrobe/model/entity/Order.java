package bg.conquerors.wardrobe.model.entity;

import bg.conquerors.wardrobe.model.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(nullable = false, name = "total_price")
    private BigDecimal totalPrice;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

}

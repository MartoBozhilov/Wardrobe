package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import bg.conquerors.wardrobe.service.AdminService;
import bg.conquerors.wardrobe.service.ProductService;
import org.hibernate.annotations.processing.Find;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;
    private final ProductService productService;

    public AdminController(AdminService adminService,ProductService productService) {
        this.adminService = adminService;
        this.productService = productService;
    }

    /*
    *
    *                               Products Controls
    *
    * */

    @GetMapping("")
    public String admin() {

        return "admin/admin";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {

        model.addAttribute("addProductDTO", new AddProductDTO());
        model.addAttribute("categoryOptions", CategoryEnum.values());
        model.addAttribute("genderOptions", GenderEnum.values());
        model.addAttribute("sizeOptions", SizeEnum.values());
        model.addAttribute("styleOptions", StyleEnum.values());

        return "admin/product/add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(AddProductDTO addProductDTO) {

        adminService.addProduct(addProductDTO);
        return "admin/admin";
    }

    private  Long itemId = -1l;
    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {

        itemId = id;
        AddProductDTO addProductDTO = adminService.getProductById(id);


        if (addProductDTO == null)
            return "error";
        model.addAttribute("addProductDTO", addProductDTO);
        model.addAttribute("categoryOptions", CategoryEnum.values());
        model.addAttribute("genderOptions", GenderEnum.values());
        model.addAttribute("sizeOptions", SizeEnum.values());
        model.addAttribute("styleOptions", StyleEnum.values());

        return "admin/product/edit-product";
    }



    @PostMapping("/edit-product")
    public String editProduct( AddProductDTO addProductDTO) {

        if (itemId == -1l)
            return "error";

        adminService.editProduct(itemId,addProductDTO);
        itemId = -1l;
        return "admin/admin";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {

        adminService.deleteProduct(id);

        return "admin/admin";
    }

    @PostMapping("/delete-product")
    public String deleteProduct() {

        if (itemId == -1l)
            return "error";

        adminService.deleteProduct(itemId);
        itemId = -1l;
        return "admin/admin";
    }

    @GetMapping("/product-grid")
    public String productGrid(Model model) {
        model.addAttribute("products", productService.getViewOfProducts());

        return "admin/product-grid";
    }



    /*
     *
     *                              Orders Controls
     *
     * */

   /* @GetMapping("/add-order")
    public String addOrder(Model model) {

        model.addAttribute("addProductDTO", new AddOrderDTO());
        model.addAttribute("categoryOptions", CategoryEnum.values());
        model.addAttribute("genderOptions", GenderEnum.values());
        model.addAttribute("sizeOptions", SizeEnum.values());
        model.addAttribute("styleOptions", StyleEnum.values());

        return "admin/Order/add-Order";
    }

    @PostMapping("/add-Order")
    public String addOrder(AddOrderDTO addOrderDTO) {

        adminService.addOrder(addOrderDTO);
        return "admin/admin";
    }

    private  Long orderId = -1l;

    @GetMapping("/edit-Order/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {

        itemId = id;
        AddOrderDTO addOrderDTO = adminService.getOrderById(id);


        if (addOrderDTO == null)
            return "error";
        model.addAttribute("addProductDTO", addOrderDTO);
        model.addAttribute("categoryOptions", CategoryEnum.values());
        model.addAttribute("genderOptions", GenderEnum.values());
        model.addAttribute("sizeOptions", SizeEnum.values());
        model.addAttribute("styleOptions", StyleEnum.values());

        return "admin/product/edit-product";
    }

    @PostMapping("/edit-order")
    public String editOrder(AddOrderDTO addOrderDTO) {

        if (itemId == -1l)
            return "error";

        adminService.editOrder(itemId,addOrderDTO);
        itemId = -1l;
        return "admin/admin";
    }

    @GetMapping("/delete-order/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {

        adminService.deleteOrder(id);

        return "admin/admin";
    }

    @PostMapping("/delete-order")
    public String deleteOrder() {

        if (itemId == -1l)
            return "error";

        adminService.deleteOrder(itemId);
        itemId = -1l;
        return "admin/admin";
    }*/
}

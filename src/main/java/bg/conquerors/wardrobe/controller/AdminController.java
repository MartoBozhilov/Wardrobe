package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.AddDiscountDTO;
import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import bg.conquerors.wardrobe.service.AdminService;
import bg.conquerors.wardrobe.service.ProductService;
import jakarta.validation.Valid;
import lombok.Data;
import org.hibernate.annotations.processing.Find;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;
    private final ProductService productService;

    public AdminController(AdminService adminService, ProductService productService) {
        this.adminService = adminService;
        this.productService = productService;
    }

    /*
     *
     *                               Products Controls
     *
     * */

    @GetMapping("")
    public String admin(Model model) {
        model.addAttribute("products",productService.getViewOfProducts());
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
        return "redirect:/admin";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {

        AddProductDTO addProductDTO = adminService.getProductById(id);


        if (addProductDTO == null)
            return "error";

        model.addAttribute("addProductDTO", addProductDTO);
        model.addAttribute("productId", id);
        model.addAttribute("categoryOptions", CategoryEnum.values());
        model.addAttribute("genderOptions", GenderEnum.values());
        model.addAttribute("sizeOptions", SizeEnum.values());
        model.addAttribute("styleOptions", StyleEnum.values());

        return "admin/product/edit-product";
    }

    @PostMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id") Long id, AddProductDTO addProductDTO) {

        adminService.editProduct(id, addProductDTO);
        return "redirect:/admin";
    }

    @GetMapping("/delete-product/{productNumber}")
    public String deleteProductGet(@PathVariable("productNumber") String productNumber) {

        adminService.deleteProduct(productNumber);

        return "redirect:/admin";
    }

    @PostMapping("/delete-product/{productNumber}")
    public String deleteProductPost(@PathVariable("productNumber") String productNumber) {

        adminService.deleteProduct(productNumber);

        return "redirect:/admin";
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



    /*
     *
     *                              Discount Controls
     *
     * */

    @GetMapping("/add-discount")
    public String addDiscount(Model model) {

        model.addAttribute("addDiscountDTO", new AddDiscountDTO());

        return "admin/discount/add-discount";
    }

    @PostMapping("/add-discount")
    public String addDiscount(AddDiscountDTO addDiscountDTO) {
        adminService.addDiscount(addDiscountDTO);
        return "admin/admin";
    }

    @GetMapping("/edit-discount/{id}")
    public String editDiscount(@PathVariable("id") Long id, Model model) {

        AddDiscountDTO addDiscountDTO = adminService.getDiscountById(id);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (addDiscountDTO == null)
            return "error";

        model.addAttribute("addDiscountDTO", addDiscountDTO);
        model.addAttribute("startDate",format.format(addDiscountDTO.getStartDate()));
        model.addAttribute("endDate",format.format(addDiscountDTO.getEndDate()));
        model.addAttribute("discountId", id);

        return "admin/discount/edit-discount";
    }

    @PostMapping("/edit-discount/{id}")
    public String editDiscount(@PathVariable("id") Long id, AddDiscountDTO addDiscountDTO) {
        adminService.editDiscount(id, addDiscountDTO);
        return "admin/admin";
    }

    @GetMapping("/delete-discount/{id}")
    public String deleteDiscountGet(@PathVariable("id") Long id) {

        adminService.deleteDiscount(id);

        return "admin/admin";
    }

    @PostMapping("/delete-discount/{id}")
    public String deleteDiscountPost(@PathVariable("id") Long id) {

        adminService.deleteDiscount(id);

        return "admin/admin";
    }
}

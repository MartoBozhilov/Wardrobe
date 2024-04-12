package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import bg.conquerors.wardrobe.service.AdminService;
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

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

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

}

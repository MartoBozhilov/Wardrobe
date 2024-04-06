package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import bg.conquerors.wardrobe.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("addProductDTO", new AddProductDTO());
        model.addAttribute("categoryOptions", CategoryEnum.values());
        model.addAttribute("genderOptions", GenderEnum.values());
        model.addAttribute("sizeOptions", SizeEnum.values());
        model.addAttribute("styleOptions", StyleEnum.values());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(AddProductDTO addProductDTO) {

        adminService.addProduct(addProductDTO);
        return "index";
    }

}

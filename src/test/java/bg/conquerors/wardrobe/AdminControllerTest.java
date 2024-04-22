package bg.conquerors.wardrobe;

import bg.conquerors.wardrobe.controller.AdminController;
import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.dto.AddOrderDTO;
import bg.conquerors.wardrobe.model.dto.AddUserDTO;
import bg.conquerors.wardrobe.model.dto.AddDiscountDTO;
import bg.conquerors.wardrobe.model.dto.SetDiscountToProductDTO;
import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import bg.conquerors.wardrobe.model.enums.OrderStatusEnum;
import bg.conquerors.wardrobe.service.AdminService;
import bg.conquerors.wardrobe.service.OrderService;
import bg.conquerors.wardrobe.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderService orderService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAdminPageRendering() {
        // Arrange

        // Act
        String viewName = adminController.admin(model);

        // Assert
        assertEquals("admin/admin", viewName);
        verify(model).addAttribute("products", productService.getViewOfProducts());
    }

    @Test
    void testAddProductPageRendering() {
        // Arrange

        // Act
        String viewName = adminController.addProduct(model);

        // Assert
        assertEquals("admin/product/add-product", viewName);
        verify(model).addAttribute(eq("addProductDTO"), any(AddProductDTO.class));
        verify(model).addAttribute("categoryOptions", CategoryEnum.values());
        verify(model).addAttribute("genderOptions", GenderEnum.values());
        verify(model).addAttribute("sizeOptions", SizeEnum.values());
        verify(model).addAttribute("styleOptions", StyleEnum.values());
    }

    @Test
    void testAddProduct() {
        // Arrange
        AddProductDTO addProductDTO = new AddProductDTO();
        Map<String, Integer> quantities = new HashMap<>();
        addProductDTO.setQuantities(quantities);

        // Act
        String viewName = adminController.addProduct(addProductDTO, bindingResult);

        // Assert
        assertEquals("redirect:/admin", viewName);
        verify(bindingResult).hasErrors();
        verify(adminService).addProduct(addProductDTO);
    }

}

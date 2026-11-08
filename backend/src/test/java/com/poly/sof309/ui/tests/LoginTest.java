package com.poly.sof309.ui.tests;

import com.poly.sof309.ui.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

/**
 * =========================================================================
 * [Y4] KỊCH BẢN CHẠY SELENIUM TEST TRÊN TRÌNH DUYỆT THỰC TẾ
 * Hỗ trợ cả chế độ có giao diện (Local) và chế độ Headless (GitHub Actions).
 *
 * Trang đăng nhập của DevClimb được phục vụ tĩnh (ví dụ VS Code Live Server).
 * Ghi đè URL bằng: -DbaseUrl=http://localhost:5500/frontend/pages/guest/auth/login.html
 * hoặc biến môi trường BASE_URL.
 * =========================================================================
 */
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    private static String resolveBaseUrl() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:5500/frontend/pages/guest/auth/login.html";
        }
        return url;
    }

    private final String BASE_URL = resolveBaseUrl();

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Kiểm tra nếu đang chạy trên CI/CD GitHub Actions thì tự bật Headless
        if (Boolean.parseBoolean(System.getenv("CI"))) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Test giao diện đăng nhập thành công")
    @Disabled("Bật lại khi đã chạy web local + cấu hình Firebase (constants.js) để chạy thật")
    public void testLoginSuccessUI() {
        driver.get(BASE_URL);
        loginPage.login("admin@example.com", "123456");
        Assertions.assertTrue(driver.getCurrentUrl().contains("/frontend/pages/user/"),
                "Sau khi đăng nhập thành công phải chuyển về trang người dùng");
    }
}

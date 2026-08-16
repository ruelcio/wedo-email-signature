package ao.grupowedo.emailssignature.automation;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;

public class RoundcubeBot {

    public static void main(String[] args) {

        // =========================================================
        // CREDENCIAIS
        // =========================================================

        String email = System.getenv("ROUNDCUBE_EMAIL");
        String password = System.getenv("ROUNDCUBE_PASSWORD");

        if (email == null || password == null) {
            throw new IllegalStateException(
                "Defina ROUNDCUBE_EMAIL e ROUNDCUBE_PASSWORD antes de executar."
            );
        }

        // =========================================================
        // HTML DA ASSINATURA
        // =========================================================

        String assinaturaHtml = """
            <table style="background-color: #fff;" role="presentation" border="0" cellspacing="0" cellpadding="0">
                <tbody>

                    <tr>
                        <td style="color: #000000; font-size: 12px; font-family: Verdana, Geneva, sans-serif;">
                            Com os melhores cumprimentos | Best regards,
                        </td>
                    </tr>

                    <tr>
                        <td style="font-size: 1px;" height="18">
                            <br>
                        </td>
                    </tr>

                </tbody>
            </table>

            <table
                style="color: #000000; font-family: Verdana, Geneva, sans-serif; background-color: #fff;"
                role="presentation"
                border="0"
                width="700"
                cellspacing="0"
                cellpadding="0"
            >
                <tbody>

                    <tr>

                        <!-- BLOCO ESQUERDO -->

                        <td valign="top" width="60%">

                            <table
                                role="presentation"
                                border="0"
                                cellspacing="0"
                                cellpadding="0"
                            >
                                <tbody>

                                    <!-- NOME -->

                                    <tr>
                                        <td style="
                                            color: #000000;
                                            font-size: 18px;
                                            font-family: Verdana, Geneva, sans-serif;
                                            font-weight: bold;
                                        ">
                                            Ruélcio Muliata
                                        </td>
                                    </tr>

                                    <!-- CARGO -->

                                    <tr>
                                        <td style="
                                            color: #7e8c8d;
                                            font-size: 15px;
                                            font-family: Verdana, Geneva, sans-serif;
                                            font-weight: 500;
                                        ">
                                            IT Intern
                                        </td>
                                    </tr>

                                    <!-- ESPAÇO -->

                                    <tr>
                                        <td style="font-size: 1px;" height="18">
                                            <br>
                                        </td>
                                    </tr>

                                    <!-- TELEFONE -->

                                    <tr>
                                        <td style="
                                            color: #000000;
                                            font-size: 14px;
                                            font-family: Verdana, Geneva, sans-serif;
                                        ">
                                            Tel:
                                            <a
                                                style="color: inherit;"
                                                href="tel:(+244) 900 900 900"
                                            >
                                                (+244) 900 900 900
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- EMAIL -->

                                    <tr>
                                        <td style="
                                            color: #000000;
                                            font-size: 14px;
                                            font-family: Verdana, Geneva, sans-serif;
                                        ">
                                            E-mail:
                                            <a
                                                style="color: inherit;"
                                                href="mailto:ruelcio.muliata@grupowedo.ao"
                                            >
                                                ruelcio.muliata@grupowedo.ao
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- WEBSITE -->

                                    <tr>
                                        <td style="
                                            color: #000000;
                                            font-size: 14px;
                                            font-family: Verdana, Geneva, sans-serif;
                                        ">
                                            Website:
                                            <a
                                                style="color: inherit;"
                                                href="https://grupowedo.ao/"
                                            >
                                                www.grupowedo.ao
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- ESPAÇO -->

                                    <tr>
                                        <td style="font-size: 1px;" height="22">
                                            <br>
                                        </td>
                                    </tr>

                                    <!-- REDES SOCIAIS -->

                                    <tr>
                                        <td>

                                            <table
                                                role="presentation"
                                                border="0"
                                                cellspacing="0"
                                                cellpadding="0"
                                            >
                                                <tbody>

                                                    <tr>

                                                        <!-- FACEBOOK -->

                                                        <td>
                                                            <a href="https://facebook.com/">
                                                                <img
                                                                    src="https://res.cloudinary.com/jqgxvpiv/image/upload/v1786117641/face-icon_nknabx.png"
                                                                    alt="Facebook"
                                                                    width="23"
                                                                    height="23"
                                                                >
                                                            </a>
                                                        </td>

                                                        <!-- ESPAÇO -->

                                                        <td
                                                            style="font-size: 1px;"
                                                            width="10"
                                                        >
                                                            <br>
                                                        </td>

                                                        <!-- LINKEDIN -->

                                                        <td>
                                                            <a href="https://linkedin.com/">
                                                                <img
                                                                    src="https://res.cloudinary.com/jqgxvpiv/image/upload/v1786117641/linkedin-icon_suefyj.png"
                                                                    alt="LinkedIn"
                                                                    width="23"
                                                                    height="23"
                                                                >
                                                            </a>
                                                        </td>

                                                        <!-- ESPAÇO -->

                                                        <td
                                                            style="font-size: 1px;"
                                                            width="10"
                                                        >
                                                            <br>
                                                        </td>

                                                        <!-- INSTAGRAM -->

                                                        <td>
                                                            <a href="https://instagram.com/">
                                                                <img
                                                                    src="https://res.cloudinary.com/jqgxvpiv/image/upload/v1786117640/insta-icon_c2yc8b.png"
                                                                    alt="Instagram"
                                                                    width="23"
                                                                    height="23"
                                                                >
                                                            </a>
                                                        </td>

                                                    </tr>

                                                </tbody>
                                            </table>

                                        </td>
                                    </tr>

                                </tbody>
                            </table>

                        </td>

                        <!-- LOGO -->

                        <td
                            align="right"
                            valign="top"
                            width="40%"
                        >
                            <img
                                src="https://res.cloudinary.com/jqgxvpiv/image/upload/v1786117639/group-icon-named_jqhquj.png"
                                alt="Grupo Wedo"
                                width="250"
                                align="right"
                            >
                        </td>

                    </tr>

                </tbody>
            </table>

            <!-- ESPAÇO ANTES DA BARRA -->

            <table
                role="presentation"
                border="0"
                cellspacing="0"
                cellpadding="0"
            >
                <tbody>

                    <tr>
                        <td
                            style="font-size: 1px;"
                            height="5"
                        >
                            <br>
                        </td>
                    </tr>

                    <!-- BARRA -->

                    <tr>
                        <td>
                            <img
                                role="presentation"
                                src="https://res.cloudinary.com/jqgxvpiv/image/upload/v1786117646/group_sign_iihdkl.gif"
                                alt=""
                                width="700"
                                height="15"
                                aria-hidden="true"
                            >
                        </td>
                    </tr>

                </tbody>
            </table>
        """;

        // =========================================================
        // PLAYWRIGHT
        // =========================================================

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                    .setHeadless(false)
            );

            Page page = browser.newPage();

            // =====================================================
            // 1. ABRIR WEBMAIL
            // =====================================================

            System.out.println("Abrindo Webmail...");

            page.navigate("https://webmail.grupowedo.ao");

            System.out.println(
                "Página aberta: " + page.url()
            );

            // =====================================================
            // 2. LOGIN
            // =====================================================

            page.locator("#user").fill(email);
            page.locator("#pass").fill(password);

            System.out.println(
                "Credenciais preenchidas."
            );

            page.locator("#login_submit").click();

            System.out.println(
                "Login enviado."
            );

            page.waitForTimeout(3000);

            // =====================================================
            // 3. OPEN
            // =====================================================

            Locator openButton =
                page.locator("#launchActiveButton");

            openButton.waitFor();

            System.out.println(
                "Botão Open encontrado."
            );

            System.out.println(
                "Botão Open visível: "
                + openButton.isVisible()
            );

            System.out.println(
                "Botão Open habilitado: "
                + openButton.isEnabled()
            );

            System.out.println(
                "Clicando no botão Open..."
            );

            openButton.click(
                new Locator.ClickOptions()
                    .setForce(true)
            );

            System.out.println(
                "Clique executado."
            );

            page.waitForTimeout(3000);

            System.out.println(
                "URL depois do clique:"
            );

            System.out.println(
                page.url()
            );

            System.out.println(
                "Título:"
            );

            System.out.println(
                page.title()
            );

            // =====================================================
            // 4. CONFIGURAÇÕES
            // =====================================================

            System.out.println(
                "Abrindo Configurações..."
            );

            Locator settings =
                page.locator("a.settings");

            settings.waitFor();

            settings.click(
                new Locator.ClickOptions()
                    .setForce(true)
            );

            page.waitForTimeout(2000);

            System.out.println(
                "Configurações abertas."
            );

            System.out.println(
                "URL: " + page.url()
            );

            // =====================================================
            // 5. IDENTIDADES
            // =====================================================

            System.out.println(
                "Abrindo Identidades..."
            );

            Locator identities =
                page.locator(
                    "a[href*='_action=identities']"
                );

            identities.waitFor();

            identities.click(
                new Locator.ClickOptions()
                    .setForce(true)
            );

            page.waitForTimeout(2000);

            System.out.println(
                "Identidades abertas."
            );

            System.out.println(
                "URL: " + page.url()
            );

            // =====================================================
            // 6. SELECIONAR IDENTIDADE
            // =====================================================

            System.out.println(
                "Selecionando identidade..."
            );

            Locator identity =
                page.locator(
                    "#identities-table tr"
                );

            identity.first().waitFor();

            identity.first().click(
                new Locator.ClickOptions()
                    .setForce(true)
            );

            page.waitForTimeout(2000);

            System.out.println(
                "Identidade selecionada."
            );

            // =====================================================
            // 7. LOCALIZAR O IFRAME
            // =====================================================

            System.out.println(
                "Procurando preferences-frame..."
            );

            Locator preferencesFrameElement =
                page.locator(
                    "#preferences-frame"
                );

            preferencesFrameElement.waitFor();

            System.out.println(
                "Iframe preferences-frame encontrado."
            );

            /*
             * IMPORTANTE:
             *
             * Não usamos:
             *
             * Frame frame = ...
             *
             * Usamos FrameLocator.
             */

            FrameLocator frame =
                page.frameLocator(
                    "#preferences-frame"
                );

            // =====================================================
            // 8. BOTÃO HTML
            // =====================================================

            System.out.println(
                "Procurando botão HTML..."
            );

            Locator htmlButton =
                frame.locator(
                    "a.mce-i-html"
                );

            htmlButton.waitFor(
                new Locator.WaitForOptions()
                    .setState(
                        WaitForSelectorState.ATTACHED
                    )
            );

            System.out.println(
                "Botão HTML encontrado."
            );

            System.out.println(
                "Botão HTML visível: "
                + htmlButton.isVisible()
            );

            System.out.println(
                "Clicando no botão HTML..."
            );

            /*
             * O botão pode estar marcado como hidden
             * pelo TinyMCE.
             *
             * Por isso usamos force.
             */

            htmlButton.click(
                new Locator.ClickOptions()
                    .setForce(true)
            );

            page.waitForTimeout(1000);

            System.out.println(
                "Modo HTML ativado."
            );

            // =====================================================
            // 9. TEXTAREA DA ASSINATURA
            // =====================================================

            System.out.println(
                "Procurando textarea da assinatura..."
            );

            Locator signatureTextarea =
                frame.locator(
                    "#rcmfd_signature"
                );

            signatureTextarea.waitFor(
                new Locator.WaitForOptions()
                    .setState(
                        WaitForSelectorState.ATTACHED
                    )
            );

            System.out.println(
                "Textarea encontrado."
            );

            System.out.println(
                "Textarea visível: "
                + signatureTextarea.isVisible()
            );

            // =====================================================
            // 10. LER ASSINATURA ATUAL
            // =====================================================

            Object currentValueObject =
                signatureTextarea.evaluate(
                    "element => element.value"
                );

            String currentValue =
                currentValueObject == null
                    ? ""
                    : currentValueObject.toString();

            System.out.println();
            System.out.println(
                "=========================================="
            );

            System.out.println(
                "ASSINATURA ANTERIOR:"
            );

            System.out.println(
                currentValue
            );

            System.out.println(
                "=========================================="
            );

            // =====================================================
            // 11. INSERIR NOVA ASSINATURA
            // =====================================================

            System.out.println(
                "Instalando assinatura..."
            );

            signatureTextarea.evaluate(
                """
                (element, html) => {

                    element.value = html;

                    element.dispatchEvent(
                        new Event('input', {
                            bubbles: true
                        })
                    );

                    element.dispatchEvent(
                        new Event('change', {
                            bubbles: true
                        })
                    );

                }
                """,
                assinaturaHtml
            );

            System.out.println(
                "HTML inserido."
            );

            // =====================================================
            // 12. VERIFICAR ALTERAÇÃO
            // =====================================================

            Object newValueObject =
                signatureTextarea.evaluate(
                    "element => element.value"
                );

            String newValue =
                newValueObject == null
                    ? ""
                    : newValueObject.toString();

            System.out.println();
            System.out.println(
                "=========================================="
            );

            System.out.println(
                "NOVA ASSINATURA:"
            );

            System.out.println(
                newValue
            );

            System.out.println(
                "=========================================="
            );

            if (!newValue.contains("Ruélcio Muliata")) {

                throw new IllegalStateException(
                    "A assinatura não foi inserida corretamente."
                );
            }

            System.out.println(
                "Assinatura inserida corretamente."
            );

            // =====================================================
            // 13. SALVAR
            // =====================================================

            System.out.println(
                "Procurando botão Salvar..."
            );

            Locator saveButton =
                frame.locator(
                    "#rcmbtnfrm102"
                );

            saveButton.waitFor(
                new Locator.WaitForOptions()
                    .setState(
                        WaitForSelectorState.ATTACHED
                    )
            );

            System.out.println(
                "Botão Salvar encontrado."
            );

            System.out.println(
                "Salvando assinatura..."
            );

            saveButton.click(
                new Locator.ClickOptions()
                    .setForce(true)
            );

            System.out.println(
                "Botão Salvar clicado."
            );

            // =====================================================
            // 14. AGUARDAR ROUNDcube
            // =====================================================

            page.waitForTimeout(3000);

            System.out.println();
            System.out.println(
                "=========================================="
            );

            System.out.println(
                "PROCESSO DE SALVAMENTO TERMINADO."
            );

            System.out.println(
                "=========================================="
            );

            System.out.println(
                "URL atual:"
            );

            System.out.println(
                page.url()
            );

            // =====================================================
            // 15. MANTER NAVEGADOR ABERTO
            // =====================================================

            System.out.println();
            System.out.println(
                "Pressiona ENTER para fechar..."
            );

            try {
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }

            browser.close();
        }
    }
}
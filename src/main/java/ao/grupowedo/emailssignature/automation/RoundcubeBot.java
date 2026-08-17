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
            <p>
                <strong>Ruélcio Muliata</strong><br>
                IT Intern<br>
                Grupo Wedo<br>
                ruelcio.muliata@grupowedo.ao
            </p>
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

            page.navigate(
                "https://webmail.grupowedo.ao"
            );

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
            // 7. LOCALIZAR IFRAME
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

            FrameLocator frame =
                page.frameLocator(
                    "#preferences-frame"
                );


            // =====================================================
            // 8. TROCAR O EDITOR PARA HTML
            // =====================================================

            System.out.println(
                "Alternando o editor para o modo HTML..."
            );

            Locator htmlToggle =
                frame.locator(
                    "button.mce-i-html, a.mce-i-html, button.mce-i-code, a.mce-i-code, button[aria-label*=\"HTML\"], button[title*=\"HTML\"]"
                );

            htmlToggle.first().waitFor(
                new Locator.WaitForOptions()
                    .setState(
                        WaitForSelectorState.ATTACHED
                    )
            );

            System.out.println(
                "Toggle do editor HTML encontrado."
            );

            System.out.println(
                "Toggle visível: "
                + htmlToggle.first().isVisible()
            );

            // O Roundcube/TinyMCE às vezes exige dois toques para sair
            // do modo visual e entrar no modo HTML de verdade.
            htmlToggle.first().click(
                new Locator.ClickOptions()
                    .setForce(true)
            );

            page.waitForTimeout(500);

            htmlToggle.first().click(
                new Locator.ClickOptions()
                    .setForce(true)
            );

            System.out.println(
                "Clique duplo no editor executado."
            );

            page.waitForTimeout(1500);

            System.out.println(
                "Modo HTML ativado."
            );


            // =====================================================
            // 9. LOCALIZAR TEXTAREA
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

            String currentValue =
                signatureTextarea
                    .evaluate(
                        "element => element.value"
                    )
                    .toString();

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

                    element.dispatchEvent(
                        new Event('blur', {
                            bubbles: true
                        })
                    );

                }
                """,
                assinaturaHtml
            );

            page.waitForTimeout(500);

            System.out.println(
                "HTML inserido."
            );


            // =====================================================
            // 12. VERIFICAR ALTERAÇÃO
            // =====================================================

            String newValue =
                signatureTextarea
                    .evaluate(
                        "element => element.value"
                    )
                    .toString();

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
            // 14. AGUARDAR SALVAMENTO
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

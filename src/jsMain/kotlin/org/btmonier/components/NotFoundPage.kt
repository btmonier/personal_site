package org.btmonier.components

import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.browser.document
import org.btmonier.model.NotFoundImages
import org.w3c.dom.HTMLElement
import kotlin.random.Random

private fun getRandom404Image(images: NotFoundImages): String {
    if (images.images.isEmpty()) {
        return "" // Fallback if no images
    }
    val randomImage = images.images[Random.nextInt(images.images.size)]
    return "images/404/$randomImage"
}

fun createNotFoundPage(images: NotFoundImages): HTMLElement {
    val randomImage = getRandom404Image(images)
    
    return document.create.div("not-found-page") {
        style = """
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 60vh;
            padding: 48px 24px;
            text-align: center;
        """.trimIndent()
        
        // Random 404 image (only show if we have images)
        if (randomImage.isNotEmpty()) {
            img(src = randomImage, alt = "404", classes = "not-found-image") {
                style = """
                    max-width: 100%;
                    max-height: 400px;
                    width: auto;
                    height: auto;
                    margin-bottom: 32px;
                    border-radius: var(--md-sys-shape-corner-medium);
                    box-shadow: var(--md-sys-elevation-2);
                """.trimIndent()
            }
        }
        
        // Large 404 number
        h1("not-found-number") {
            style = """
                font: var(--md-sys-typescale-display-large);
                color: var(--md-sys-color-primary);
                margin-bottom: 16px;
                font-weight: 700;
            """.trimIndent()
            +"404"
        }
        
        // Error message
        h2("not-found-title") {
            style = """
                font: var(--md-sys-typescale-headline-medium);
                color: var(--md-sys-color-on-surface);
                margin-bottom: 16px;
            """.trimIndent()
            +"Page Not Found"
        }
        
        // Description
        p("not-found-description") {
            style = """
                font: var(--md-sys-typescale-body-large);
                color: var(--md-sys-color-on-surface-variant);
                margin-bottom: 32px;
                max-width: 500px;
            """.trimIndent()
            +"You seem to be lost."
        }
        
        // Action button to go home
        a(href = "/about", classes = "md-button not-found-button") {
            style = """
                margin-top: 8px;
                text-decoration: none;
            """.trimIndent()
            span("material-icons") {
                style = "margin-right: 8px; font-size: 20px;"
                +"home"
            }
            +"Go to Home"
        }
    }
}


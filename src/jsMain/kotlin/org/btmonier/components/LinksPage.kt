package org.btmonier.components

import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.browser.document
import org.btmonier.model.Link
import org.w3c.dom.HTMLElement

fun createLinksPage(links: List<Link>): HTMLElement {
    return document.create.div {
        div("page-header") {
            h1("page-title") { +"External Links" }
            p("page-subtitle") { +"Find me elsewhere on the web" }
        }
        
        div("links-grid") {
            links.forEach { link ->
                a(href = link.url, target = "_blank", classes = "link-card md-card md-card-elevated") {
                    div("link-icon-container") {
                        i(classes = link.icon ?: "fa-solid fa-link") {}
                    }
                    div("link-content") {
                        p("link-label") { +link.label }
                        link.description?.let { desc ->
                            p("link-description") { +desc }
                        }
                    }
                    span("material-icons link-arrow") { +"arrow_forward" }
                }
            }
        }
    }
}


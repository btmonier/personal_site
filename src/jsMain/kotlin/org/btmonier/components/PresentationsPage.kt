package org.btmonier.components

import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.browser.document
import org.btmonier.model.Presentation
import org.w3c.dom.HTMLElement

fun createPresentationsPage(presentations: List<Presentation>): HTMLElement {
    val talks = presentations.filter { it.type == "talk" }
    val posters = presentations.filter { it.type == "poster" }
    
    // Get all unique years across both talks and posters
    val allYears = presentations.map { it.year }.distinct().sortedDescending()
    
    // Group by year
    val talksByYear: Map<Int, List<Presentation>> = talks.groupBy { it.year }
    val postersByYear: Map<Int, List<Presentation>> = posters.groupBy { it.year }
    
    return document.create.div {
        div("page-header") {
            h1("page-title") { +"Presentations" }
            p("page-subtitle") { +"Conference talks and poster presentations" }
        }
        
        div("page-with-sidebar") {
            // Year Navigation Sidebar
            nav("year-nav-sidebar") {
                id = "pres-year-nav"
                div("year-nav-sidebar-title") { +"Years" }
                div("year-nav-list") {
                    allYears.forEach { year ->
                        a(href = "#pres-year-$year", classes = "year-nav-item") {
                            attributes["data-year"] = year.toString()
                            +year.toString()
                        }
                    }
                }
            }
            
            // Main Content
            div("page-main-content") {
                div("presentations-by-year") {
                    allYears.forEach { year ->
                        val yearTalks = talksByYear[year] ?: emptyList()
                        val yearPosters = postersByYear[year] ?: emptyList()
                        
                        if (yearTalks.isNotEmpty() || yearPosters.isNotEmpty()) {
                            div("year-section") {
                                id = "pres-year-$year"
                                attributes["data-year"] = year.toString()
                                h2("year-section-title") { +year.toString() }
                                
                                // Talks for this year
                                if (yearTalks.isNotEmpty()) {
                                    div("presentations-subsection") {
                                        h3("presentations-subsection-title") {
                                            span("material-icons") { +"mic" }
                                            +"Talks"
                                            span("count-badge") { +"${yearTalks.size}" }
                                        }
                                        div("year-section-items") {
                                            yearTalks.forEach { presentation ->
                                                renderPresentationCard(presentation)
                                            }
                                        }
                                    }
                                }
                                
                                // Posters for this year
                                if (yearPosters.isNotEmpty()) {
                                    div("presentations-subsection") {
                                        h3("presentations-subsection-title") {
                                            span("material-icons") { +"dashboard" }
                                            +"Posters"
                                            span("count-badge") { +"${yearPosters.size}" }
                                        }
                                        div("year-section-items") {
                                            yearPosters.forEach { presentation ->
                                                renderPresentationCard(presentation)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun DIV.renderPresentationCard(presentation: Presentation) {
    div("presentation-card md-card md-card-elevated") {
        div("presentation-content") {
            h3("presentation-title") { +presentation.title }
            p("presentation-authors") {
                val authorsText = presentation.authors.joinToString(", ")
                val parts = authorsText.split("Brandon Monier")
                parts.forEachIndexed { index, part ->
                    +part
                    if (index < parts.size - 1) {
                        b { +"Brandon Monier" }
                    }
                }
            }
            p("presentation-venue") {
                span("material-icons") { +"location_on" }
                +presentation.venue
            }
        }
    }
}

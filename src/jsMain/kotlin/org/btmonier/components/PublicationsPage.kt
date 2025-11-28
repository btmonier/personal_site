package org.btmonier.components

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.browser.document
import org.btmonier.model.Publication
import org.btmonier.model.ScholarStats
import org.w3c.dom.HTMLElement

private fun formatDate(yyyymmdd: String): String {
    return try {
        // Parse YYYYmmdd format using kotlinx-datetime
        val year = yyyymmdd.substring(0, 4).toInt()
        val month = yyyymmdd.substring(4, 6).toInt()
        val day = yyyymmdd.substring(6, 8).toInt()
        val date = LocalDate(year, month, day)
        
        val monthName = date.month.name.lowercase().replaceFirstChar { it.uppercase() }
        "$monthName ${date.dayOfMonth}, ${date.year}"
    } catch (e: Exception) {
        // Fallback to original string if parsing fails
        yyyymmdd
    }
}

fun createPublicationsPage(publications: List<Publication>, scholarStats: ScholarStats): HTMLElement {
    val years = publications.map { it.year }.distinct().sortedDescending()
    val pubsByYear: Map<Int, List<Publication>> = publications.groupBy { it.year }
    
    return document.create.div {
        div("page-header") {
            h1("page-title") { +"Publications" }
            p("page-subtitle") { +"Academic papers and research contributions" }
        }
        
        // Scholar Overview Section
        div("scholar-overview md-card md-card-elevated") {
            h2("scholar-overview-title") {
                span("material-icons") { +"analytics" }
                +"Overview"
            }
            p("scholar-overview-subtitle") {
                +"Citation metrics from "
                a(href = "https://scholar.google.com/citations?user=buYGhlYAAAAJ&hl=en", target = "_blank") {
                    +"Google Scholar"
                }
            }
            
            div("scholar-overview-content") {
                // Stats Table
                div("scholar-stats-container") {
                    table("scholar-stats-table") {
                        thead {
                            tr {
                                th { +"Metric" }
                                th { +"All Time" }
                                th { +"Since 2020" }
                            }
                        }
                        tbody {
                            tr {
                                td { +"Citations" }
                                td("stat-value") { +scholarStats.citations.all.toString() }
                                td("stat-value") { +scholarStats.citations.since2020.toString() }
                            }
                            tr {
                                td { +"h-index" }
                                td("stat-value") { +scholarStats.hIndex.all.toString() }
                                td("stat-value") { +scholarStats.hIndex.since2020.toString() }
                            }
                            tr {
                                td { +"i10-index" }
                                td("stat-value") { +scholarStats.i10Index.all.toString() }
                                td("stat-value") { +scholarStats.i10Index.since2020.toString() }
                            }
                        }
                    }
                }
                
                // Chart Container
                div("scholar-chart-container") {
                    h3("scholar-chart-title") { +"Citations by Year" }
                    p("scholar-chart-updated") {
                        span("material-icons") { +"update" }
                        +"Last updated: ${formatDate(scholarStats.lastUpdated)}"
                    }
                    div("scholar-chart-wrapper") {
                        canvas {
                            id = "citations-chart"
                        }
                    }
                }
            }
        }
        
        div("page-with-sidebar") {
            // Year Navigation Sidebar
            nav("year-nav-sidebar") {
                id = "pub-year-nav"
                div("year-nav-sidebar-title") { +"Years" }
                div("year-nav-list") {
                    years.forEach { year ->
                        a(href = "#pub-year-$year", classes = "year-nav-item") {
                            attributes["data-year"] = year.toString()
                            +year.toString()
                        }
                    }
                }
            }
            
            // Main Content
            div("page-main-content") {
                div("publications-list") {
                    years.forEach { year ->
                        val pubs = pubsByYear[year] ?: emptyList()
                        div("year-section") {
                            id = "pub-year-$year"
                            attributes["data-year"] = year.toString()
                            h2("year-section-title") { +year.toString() }
                            div("year-section-items") {
                                pubs.forEach { pub ->
                                    div("publication-card md-card md-card-elevated") {
                                        div("publication-content") {
                                            h3("publication-title") {
                                                if (pub.url != null) {
                                                    a(href = pub.url, target = "_blank") { +pub.title }
                                                } else {
                                                    +pub.title
                                                }
                                            }
                                            p("publication-authors") {
                                                val authorsText = pub.authors.joinToString(", ")
                                                val parts = authorsText.split("Brandon Monier")
                                                parts.forEachIndexed { index, part ->
                                                    +part
                                                    if (index < parts.size - 1) {
                                                        b { +"Brandon Monier" }
                                                    }
                                                }
                                            }
                                            p("publication-journal") {
                                                +pub.journal
                                                pub.volume?.let { volume ->
                                                    +", $volume"
                                                }
                                                pub.pages?.let { pages ->
                                                    +": $pages"
                                                }
                                            }
                                            if (pub.doi != null) {
                                                div("publication-actions") {
                                                    a(href = "https://doi.org/${pub.doi}", target = "_blank", classes = "md-button md-button-tonal") {
                                                        span("material-icons") { +"open_in_new" }
                                                        +"View Paper"
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
    }
}

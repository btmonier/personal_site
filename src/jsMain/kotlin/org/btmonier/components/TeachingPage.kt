package org.btmonier.components

import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.browser.document
import org.btmonier.model.Teaching
import org.w3c.dom.HTMLElement

fun createTeachingPage(teaching: List<Teaching>): HTMLElement {
    // Extract years from period (handles formats like "2023" or "2014-2017")
    fun extractYear(period: String): Int {
        return period.split("-").first().trim().toIntOrNull() ?: 0
    }
    
    // Get all unique years sorted descending
    val allYears = teaching.map { extractYear(it.period) }.distinct().sortedDescending()
    
    // Group by year
    val teachingByYear: Map<Int, List<Teaching>> = teaching.groupBy { extractYear(it.period) }
    
    return document.create.div {
        div("page-header") {
            h1("page-title") { +"Teaching" }
            p("page-subtitle") { +"Courses and educational contributions" }
        }
        
        div("page-with-sidebar") {
            // Year Navigation Sidebar
            nav("year-nav-sidebar") {
                id = "teaching-year-nav"
                div("year-nav-sidebar-title") { +"Years" }
                div("year-nav-list") {
                    allYears.forEach { year ->
                        a(href = "#teaching-year-$year", classes = "year-nav-item") {
                            attributes["data-year"] = year.toString()
                            +year.toString()
                        }
                    }
                }
            }
            
            // Main Content
            div("page-main-content") {
                div("teaching-by-year") {
                    allYears.forEach { year ->
                        val yearTeaching = teachingByYear[year] ?: emptyList()
                        
                        if (yearTeaching.isNotEmpty()) {
                            div("year-section") {
                                id = "teaching-year-$year"
                                attributes["data-year"] = year.toString()
                                h2("year-section-title") { +year.toString() }
                                
                                div("year-section-items") {
                                    yearTeaching.forEach { course ->
                                        div("teaching-card md-card md-card-elevated") {
                                            div("teaching-header") {
                                                h3("teaching-title") {
                                                    if (course.url != null) {
                                                        a(href = course.url, target = "_blank") { +course.title }
                                                    } else {
                                                        +course.title
                                                    }
                                                }
                                                span("teaching-period") { +course.period }
                                            }
                                            div("teaching-meta") {
                                                p("teaching-institution") {
                                                    span("material-icons") { +"location_city" }
                                                    +course.institution
                                                }
                                                course.participants?.let { count ->
                                                    p("teaching-participants") {
                                                        span("material-icons") { +"groups" }
                                                        +"$count participants"
                                                    }
                                                }
                                            }
                                            course.description?.let { desc ->
                                                p("teaching-description") { +desc }
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

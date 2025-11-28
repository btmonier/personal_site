package org.btmonier.components

import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.browser.document
import org.btmonier.model.EducationData
import org.btmonier.model.SkillsData
import org.w3c.dom.HTMLElement

// Section definitions for navigation
private data class ExpSection(val id: String, val label: String, val icon: String)

private val experienceSections = listOf(
    ExpSection("exp-education", "Education", "school"),
    ExpSection("exp-research", "Research", "science"),
    ExpSection("exp-skills", "Skills", "build"),
    ExpSection("exp-mentoring", "Mentoring", "groups")
)

fun createExperiencePage(education: EducationData, skills: SkillsData): HTMLElement {
    return document.create.div {
        div("page-header") {
            h1("page-title") { +"Experience" }
            p("page-subtitle") { +"Education, research background, and skills" }
        }
        
        div("page-with-sidebar") {
            // Section Navigation Sidebar
            nav("section-nav-sidebar") {
                id = "exp-section-nav"
                div("section-nav-sidebar-title") { +"Sections" }
                div("section-nav-list") {
                    experienceSections.forEach { section ->
                        a(href = "#${section.id}", classes = "section-nav-item") {
                            attributes["data-section"] = section.id
                            span("material-icons") { +section.icon }
                            +section.label
                        }
                    }
                }
            }
            
            // Main Content
            div("page-main-content") {
                // Education Section
                if (education.degrees.isNotEmpty()) {
                    div("experience-section") {
                        id = "exp-education"
                        attributes["data-section"] = "exp-education"
                        h2("experience-section-title") {
                            span("material-icons") { +"school" }
                            +"Education"
                        }
                        div("education-list") {
                            education.degrees.sortedByDescending { it.year }.forEach { edu ->
                                div("education-card md-card md-card-elevated") {
                                    div("education-header") {
                                        div("education-degree-info") {
                                            h3("education-degree") { +"${edu.degree} in ${edu.field}" }
                                            p("education-institution") {
                                                span("material-icons") { +"account_balance" }
                                                +edu.institution
                                            }
                                            edu.department?.let { dept ->
                                                p("education-department") { +dept }
                                            }
                                        }
                                        span("education-year") { +edu.year.toString() }
                                    }
                                    edu.thesis?.let { thesis ->
                                        div("education-thesis") {
                                            span("education-thesis-label") { +"Thesis: " }
                                            if (edu.thesisUrl != null) {
                                                a(href = edu.thesisUrl, target = "_blank", classes = "education-thesis-link") {
                                                    +thesis
                                                    span("material-icons") { +"open_in_new" }
                                                }
                                            } else {
                                                span("education-thesis-text") { +thesis }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                // Research Experience Section
                if (education.experience.isNotEmpty()) {
                    div("experience-section") {
                        id = "exp-research"
                        attributes["data-section"] = "exp-research"
                        h2("experience-section-title") {
                            span("material-icons") { +"science" }
                            +"Research Experience"
                        }
                        div("experience-list") {
                            education.experience.forEach { exp ->
                                div("experience-card md-card md-card-elevated") {
                                    div("experience-header") {
                                        div("experience-info") {
                                            h3("experience-position") { +exp.position }
                                            p("experience-institution") {
                                                span("material-icons") { +"business" }
                                                +exp.institution
                                            }
                                            p("experience-location") {
                                                span("material-icons") { +"location_on" }
                                                +exp.location
                                            }
                                            exp.advisor?.let { advisor ->
                                                p("experience-advisor") {
                                                    span("material-icons") { +"person" }
                                                    +"Advisor: $advisor"
                                                }
                                            }
                                        }
                                        span("experience-period") { +exp.period }
                                    }
                                    if (exp.highlights.isNotEmpty()) {
                                        ul("experience-highlights") {
                                            exp.highlights.forEach { highlight ->
                                                li { +highlight }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                // Skills Section
                if (skills.categories.isNotEmpty()) {
                    div("experience-section") {
                        id = "exp-skills"
                        attributes["data-section"] = "exp-skills"
                        h2("experience-section-title") {
                            span("material-icons") { +"build" }
                            +"Skills"
                        }
                        div("skills-grid") {
                            skills.categories.forEach { category ->
                                div("skill-category md-card md-card-outlined") {
                                    div("skill-category-header") {
                                        span("material-icons") { +category.icon }
                                        h3("skill-category-name") { +category.name }
                                    }
                                    div("skill-items") {
                                        category.items.forEach { item ->
                                            span("skill-chip") { +item }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                // Mentoring Section
                div("experience-section") {
                    id = "exp-mentoring"
                    attributes["data-section"] = "exp-mentoring"
                    h2("experience-section-title") {
                        span("material-icons") { +"groups" }
                        +"Mentoring & Leadership"
                    }
                    
                    // Summary and Certifications
                    div("mentoring-overview md-card md-card-elevated") {
                        p("mentoring-summary") {
                            span("material-icons") { +"emoji_people" }
                            +skills.mentoring.summary
                        }
                        if (skills.mentoring.certifications.isNotEmpty()) {
                            div("mentoring-certifications") {
                                skills.mentoring.certifications.forEach { cert ->
                                    div("certification-item") {
                                        span("material-icons") { +"verified" }
                                        +cert
                                    }
                                }
                            }
                        }
                    }
                    
                    // Mentees
                    if (skills.mentoring.mentees.isNotEmpty()) {
                        h3("mentees-title") { +"Students Mentored" }
                        div("mentees-grid") {
                            skills.mentoring.mentees.sortedByDescending { it.year }.forEach { mentee ->
                                div("mentee-card md-card md-card-outlined") {
                                    div("mentee-header") {
                                        h4("mentee-name") { +mentee.name }
                                        span("mentee-year") { +mentee.year }
                                    }
                                    div("mentee-details") {
                                        span("mentee-level") { +mentee.level }
                                        span("mentee-duration") { +mentee.duration }
                                    }
                                    p("mentee-institution") { +mentee.institution }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


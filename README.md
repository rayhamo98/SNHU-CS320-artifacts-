# Rimon Hamo – Computer Science Portfolio

## CS-320: Software Test, Automation & QA  
Feb-27-2026

### Module 8 Journal – Final Course Artifacts

**Project One Artifacts** (submitted to portfolio)  
- [Contact.java](Contact.java)  
- [ContactService.java](ContactService.java)  
- [ContactTest.java](ContactTest.java)  
- [ContactServiceTest.java](ContactServiceTest.java)  

**Project Two Artifact** (submitted to portfolio)  
- [Summary and Reflections Report.pdf](Summary-and-Reflections-Report.pdf)  

---

### Course Reflections

**How can I ensure that my code, program, or software is functional and secure?**  
I ensure functionality and security through a combination of Test-Driven Development (TDD), comprehensive unit testing, and strict input validation. In Project One, I wrote JUnit test suites (ContactTest.java and ContactServiceTest.java) that covered positive paths, boundary conditions, null checks, and invalid data scenarios. This caught defects immediately rather than later in the process. For security, I implemented field-length checks, character restrictions, and exception handling to prevent invalid data from entering the system. I also use Git for version control and plan to integrate CI/CD pipelines with automated security scans in future work. The key lesson from this course is that testing is not an afterthought—it is the primary way to prove software is both functional and secure.

**How do I interpret user needs and incorporate them into a program?**  
I begin by thoroughly reviewing requirements documents and user stories, then break them down into specific, testable acceptance criteria. In the Contact Service project, the requirements specified exact field constraints (e.g., first/last name 1–10 characters, phone exactly 10 digits, unique IDs). I translated these directly into validation logic inside the Contact class and corresponding JUnit assertions. When requirements are unclear, I ask clarifying questions and create simple user stories or personas. This course taught me that the strongest programs come from mapping every user need to a verifiable test, ensuring the final product truly solves the intended problem.

**How do I approach designing software?**  
My design approach is modular, testable, and maintainable from the start. I follow object-oriented principles (separation of concerns) and design with testing in mind. For Project One, I created a simple Contact data class and a separate ContactService class for business logic—this made both components easy to test independently. I sketch basic class diagrams or pseudocode before coding, then immediately begin writing tests. I embrace iterative refinement: build a minimal working version, run the full test suite, fix issues, and repeat. This course reinforced that good design is not just about making the program work today, but making it easy to maintain, scale, and trust tomorrow.

This course has transformed how I think about software quality. I now see testing and automation as core parts of development rather than separate phases. The skills I practiced—writing JUnit tests, enforcing requirements through code, and reflecting on design choices—will be foundational in every future project and job I pursue.

---

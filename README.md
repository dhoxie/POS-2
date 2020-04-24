# POS-2

PoS^2 is a point-of-sale system for car garages.

| **Team**       |
| -------------- |
| Kayla Lambert  |
| Daylyn Hoxie   |
| Spencer Curley |
| Luke Mattfeld  |

---

## Project Structure

- `possq` holds the maven project.
- `SpencersTestCode` holds test code for PDF and email generation

---

## Guidelines for reporting issues

- [Bug Reporting](./.github/ISSUE_TEMPLATE/bug_report.md)
- [Feature Request](./.github/ISSUE_TEMPLATE/feature_request.md)
- [Engineering Issues](./.github/ISSUE_TEMPLATE/engineering-issue-template.md)
- [User Stories](./.github/ISSUE_TEMPLATE/user-story-template.md)

---

## Guidelines for branch creation

New branches should be created with the following name format:

```git
<group_tag>/#<issue_num>-<name>
```

Where `<group_tag>` is as defined below,
`<issue_num>` is the issue number associated with this branch,
and `<name>` is the concise, descriptive title for the branch.

Groups:
| Group tag | Description      |
| --------- | ---------------- |
| doc       | documentation    |
| test      | testing          |
| new       | new feature      |
| fix       | bug or issue fix |
| wip       | work in progress |
| junk      | experimentation  |

Example: For issue 42 "Add testing to data classes", an appropriate branch name would be

```git
test/#42-data-class-tests
```

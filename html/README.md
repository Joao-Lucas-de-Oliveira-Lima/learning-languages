# HTML Studies

## Tools

- **Image Placeholders**:
  - [Placehold.io](https://placehold.co/)
  - [Lorem Ipsum](https://loremipsum.io/21-of-the-best-placeholder-image-generators)
  - [Unsplash](https://unsplash.com/)
  - [Gratisography](https://gratisography.com/)
  - [Pixabay](https://pixabay.com/)

- **Image Editing**:
  - [IrfanView](https://www.irfanview.com/) - For resizing images
  - [TinyPNG](https://tinypng.com/) - For compressing file size without losing quality

- **Validation and Documentation**:
  - [HTML Validator](https://validator.w3.org/) - For validating HTML content
  - [Mozilla Developer Network](https://developer.mozilla.org/en-US/docs/Web/HTML) - Official HTML documentation

## Lessons Overview

### 01 - HTML Basics & CSS Integration
- Creating an HTML5 file
- Adding metadata
- CSS integration methods:
  - External CSS
  - Internal CSS (within the `<head>` tag)
  - Inline CSS

### 02 - Basic HTML Tags
- HTML Entities
- Paragraph hierarchy
- Text formatting:
  - `<strong>` for strong emphasis
  - `<em>` for emphasis
- Special tags:
  - `<abbr>` for abbreviations
  - `<address>` for contact information

### 03 - Lists
- `<ol>` - Ordered lists
- `<ul>` - Unordered lists
- `<dl>` - Description lists

### 04 - Navigation
- Creating navigation bars
- Link types:
  - Internal links (to page sections)
  - External links (to other web pages)
  - Download links (for local files)

### 05 - Images
- Image implementation
- Lazy loading for better performance
- `<figure>` and `<figcaption>` for image descriptions

### 06 - Semantic HTML
- Major semantic elements:
  - `<main>` - Main content
  - `<header>` - Introductory content
  - `<footer>` - Footer content
  - `<aside>` - Sidebar content
  - `<article>` - Independent content
- Interactive elements:
  - `<details>` and `<summary>` for collapsible content

### 07 - Tables
- Table structure:
  - `<table>` - Container
  - `<thead>` - Table header
  - `<tbody>` - Table body
  - `<tfoot>` - Table footer
  - `<tr>` - Table row
  - `<th>` - Table header cell
  - `<td>` - Table data cell

### 08 - Forms
- Form elements:
  - `<form>` - Container
  - `<input>` types:
    - `text` - Text input
    - `password` - Password input
    - `tel` - Telephone number
    - `number` - Numeric input
    - `radio` - Radio buttons
    - `checkbox` - Checkboxes
  - `<textarea>` - Multi-line text input
  - `<button>` types:
    - `submit` - Form submission
    - `reset` - Form reset
- Testing with [HTTPBin](https://httpbin.org/post)

## Best Practices

- Maintain a clear heading hierarchy, generally not exceeding `<h3>` in a single document
- Use semantic elements whenever possible; reserve `<div>` and `<span>` for when no semantic alternative exists
- Include only one `<h1>` tag per page
- Avoid using the GET method for form submission with sensitive data, as it exposes information in URL parameters
- Ensure proper nesting of elements according to HTML specifications
- Use appropriate alt text for images to improve accessibility
- Implement responsive design principles for better user experience across devices
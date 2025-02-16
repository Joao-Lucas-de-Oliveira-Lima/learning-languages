# HTML Studies

## Tools

- To use placeholder images in website development, you can use the following sites:
  - [placehold.io](https://placehold.co/)
  - [loremipsum](https://loremipsum.io/21-of-the-best-placeholder-image-generators)
  - [unsplash](https://unsplash.com/)
  - [gratisography](https://gratisography.com/)
  - [pixabay](https://pixabay.com/)

- To resize images:
  - [irfanview](https://www.irfanview.com/)

- To compress file size without losing quality:
  - [tinypng](https://tinypng.com/)

- To validate HTML content, you can use the following site:
  - [validator](https://validator.w3.org/)

- For official HTML documentation, refer to Mozilla Developer Network:
  - [developer.mozilla](https://developer.mozilla.org/en-US/docs/Web/HTML)

## Content Order

- **01_lesson**
  - Creating an HTML5 file
  - Adding metadata
  - Defining internal CSS within the `<head>` tag

- **02_lesson**
  - HTML Entities
  - Understanding paragraph hierarchy
  - Importing external CSS
  - Using abbreviations with the `<abbr>` tag
  - Emphasizing syntax with `<strong>` and `<em>` tags
  - Using the `<address>` tag to indicate an address

- **03_lesson**
  - Using the `<ol>` tag to create an ordered list
  - Using the `<ul>` tag to create an unordered list
  - Using the `<dl>` tag to create a description list

- **04_lesson**
  - Creating a navigation bar
  - Using links to reference internal HTML components
  - Using links to reference external `.html` files
  - Using a link to download a local file

- **05_lesson**
  - Lazy loading images for better performance
  - Figures

- **06_lesson**
  - Semantic HTML
    - `<main>`
    - `<footer>`
    - `<aside>`
    - `<header>`
    - `<article>`
  - Using `<details>` and `<summary>` tags to collapse content

- **07_lesson**
  - Tables

- **08_lesson**
  - Forms
    - text
    - password
    - tel
    - number
    - radio-button
    - checkbox
    - textarea
    - submit
    - reset
  - HTTP API test with [httpbin](https://httpbin.org/post)

## Best Practices

- Ideally, an HTML document should maintain a header hierarchy up to `<h3>`.
- Always prioritize semantic elements. Only use generic elements like `<div>` or `<span>` when there is no appropriate semantic alternative.
- When sending form data to APIs, avoid using the GET method since all data will be exposed in the URL query parameters. Sensitive information, such as passwords and credit card details, should never be transmitted this way.
- Each page must contain only one `<h1>` tag.

# CSS Studies

## Important Links

- [CSS Validator](https://jigsaw.w3.org/css-validator/)
- [Specificity Calculator](https://specificity.keegan.st/)
- [Coolors](https://coolors.co/) - Tool for color recommendations and accessibility contrast validation
- [WebAIM](https://webaim.org/) - Tool for evaluating color contrasts for accessibility
- [Google Fonts](https://fonts.google.com/selection/embed) - Repository for finding and using fonts

## Lessons Overview

### 01 - CSS Use Cases
- CSS declaration methods:
  - External CSS
  - Internal CSS
  - Inline CSS

### 02 - Basic Selectors
- Simple selectors:
  - Element selectors: `h1`
  - Class selectors: `.class`
  - ID selectors: `#id`
  - Global selector: `*`
  - `!important` declaration

### 03 - Colors
- `background-color` property
- Color representation formats:
  - Color names
  - RGB: `rgb(red, green, blue)`
  - RGBA: `rgba(red, green, blue, alpha)`
  - Hexadecimal: `#RRGGBB`
  - Hexadecimal shorthand: `#RGB`
  - HSL: `hsl(hue, saturation, lightness)`
  - HSLA: `hsla(hue, saturation, lightness, alpha)`

### 04 - Size Units
- Element sizing units:
  - `px` (pixels)
  - `rem` (relative to root element)
  - `ch` (character width)
  - `%` (percentage)

### 05 - Box Model
- `box-sizing` and `content-box`
- `text-decoration-underline` and `text-underline-offset`
- `em` media unit
- Box model components:
  - `border`
  - `width`/`height`
  - `margin`
  - `padding`

### 06 - Typography
- `text-transform` (uppercase, lowercase)
- `text-decoration` (overline, underline)
- `font-family` (internal and external like Google Fonts)
- `font-weight` and `font-style` for bold and italic
- `letter-spacing`
- `word-spacing`
- `line-height`
- `text-align` (justify and others)

### 07 - Link Styling
- Pseudo-classes:
  - `a:visited`
  - `a:hover`
  - `a:focus`
  - `a:active`

### 08 - List Styling
- `list-style-type` (change marker)
- `list-style-image` (add a URL as marker)
- `list-style-position` (change padding before or after marker)
- `::marker` (pseudo-element for markers)
- `li:nth-child(odd)` or `li:nth-child(even)` for alternating styles

### 10 - Display Inline-Block
- Using `display: block` on inline elements to add block element behaviors
- `display: inline-block` as a middle ground, allowing inline behavior with some block element features

### 11 - Float
- `float` property
- `clear` property
- `display: flow-root`

### 12 - Columns
- The `columns` property enables responsive multi-column layouts.
- `column-rule` adds a separator line between columns.
- `column-gap` sets the space between columns.
- `break-inside: avoid;` prevents elements from breaking across columns.
- `white-space: nowrap;` keeps the content of an element on a single line, avoiding line breaks when resizing.
- `column-span: all;` allows an element to span across all columns.

## CSS Properties

- `font-family`: Specifies a prioritized list of font family names and/or generic family names for the selected element. Given a font family, the browser will use the first font available on the user's system.
- `!important`: Adds more importance to the rule, overriding all other related properties.
- The `cursor` property changes the cursor type when hovering over an element. It has semantic value and should be used appropriately.
- Inline elements cannot have their height, width, or vertical margins modified as they don't create a box model by themselves. Their size adapts to the parent element's size. To add these behaviors, use `display: inline-block`.

## Inheritance

Some CSS properties are inheritable, meaning they can be transferred to child elements.

Example:
```css
body {
    font-size: 20px;
}
```
All elements inside the body will have their font size affected.

For properties that are not inherited by default (like borders), you can use:
```css
body {
    border: 1px solid black;
}

main, article, section {
    border: inherit; 
}
```
In the CSS above, the main, section, and article tags inherit properties from their parent tags in this order: body > main, main > section, section > article. This way, body properties propagate to the innermost children.

## Hierarchy

- Selectors with the same specificity will be applied procedurally, meaning the last selector in the CSS file will be the one that's actually applied:
```css
h1 {
    color: pink;
}
h1 {
    color: red;
}
```
The color applied to h1 elements will be red because it's the last one in the file.

## Best Practices

- Avoid using ID selectors. Prioritize class selectors for better hierarchy management.
- To maintain slightly different colors, you can change just the hue attribute of the `hsl()` property, allowing for subtly different colors.
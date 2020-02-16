# Changelog
All notable changes to this project will be documented in this file.

## [Unreleased]

## [0.0.2-0213] - 2020-02-13
### Added
 - Working memory buttons and memory. Can only save one value. Should there be more ? 
 
### Changed
 - Slight change in the display. The equation now wraps.
 - Can now immediately use the result of the precedent operation for a new operation


## [0.0.2] - 2020-02-12
### Changed
 - Reworked the way the calculator works. The calculator works in a "continious" way, i.e you can do operations after operation without clearing the display. When pressing "=" the total operation (displayed in the top line) is calculated in a "linear" fashion, in the order it was inputed, regardless of the operator order rule.

## [0.0.1] - 2020-02-08
### Added
 - A barebone calculator with a simple UI and button fonctionality (for most of them), a display for the current value and the current operation being performed. Also the different parts resize with the window.
 - Able to perform calculation and operation with 2 numbers ("=" is used after 2 numbers have been entered). Operation with more than 2 numbers are buggy and should not be performed
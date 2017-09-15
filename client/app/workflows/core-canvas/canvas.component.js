'use strict';

import CanvasController from './canvas.controller.js';
import CanvasTemplate from './canvas.html';
import less from './canvas.less';

const CanvasComponent = {
  bindings: {
    'workflow': '<'
  },
  controller: CanvasController,
  templateUrl: CanvasTemplate
};

export default CanvasComponent;

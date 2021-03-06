package org.jfxvnc.net.rfb.render.rect;

import org.jfxvnc.net.rfb.codec.IEncodings;

/*
 * #%L
 * RFB protocol
 * %%
 * Copyright (C) 2015 comtel2000
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


public class CopyImageRect extends ImageRect {

    protected final int srcX;
    protected final int srcY;

    public CopyImageRect(int x, int y, int width, int height, int srcx, int srcy) {
	super(x, y, width, height);
	this.srcX = srcx;
	this.srcY = srcy;
    }

    public int getSrcX() {
	return srcX;
    }

    public int getSrcY() {
	return srcY;
    }

    @Override
    public int getEncodingType() {
	return IEncodings.COPY_RECT;
    }
    
    @Override
    public String toString() {
	return "CopyImageRect [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", srcX=" + srcX + ", srcY=" + srcY + "]";
    }
}

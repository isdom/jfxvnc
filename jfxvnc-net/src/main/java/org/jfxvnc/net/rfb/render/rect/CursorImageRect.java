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

public class CursorImageRect extends ImageRect {

    private final byte[] bitmask;
    private final int[] pixels;

    public CursorImageRect(int x, int y, int width, int height, int[] pixels, byte[] bitmask) {
	super(x, y, width, height);
	this.pixels = pixels;
	this.bitmask = bitmask;
    }

    public int[] getPixels() {
	return pixels;
    }

    public byte[] getBitmask() {
	return bitmask;
    }

    @Override
    public int getEncodingType() {
	return IEncodings.CURSOR;
    }
    
    @Override
    public String toString() {
	return "CursorImageRect [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", bitmask.length=" + (bitmask != null ? bitmask.length : "null")
		+ ", pixels.length=" + (pixels != null ? pixels.length : "null") + "]";
    }

}

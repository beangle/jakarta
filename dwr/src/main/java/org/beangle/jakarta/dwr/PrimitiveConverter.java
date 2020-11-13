/*
 * Beangle, Agile Development Scaffold and Toolkits.
 *
 * Copyright © 2005, The Beangle Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.beangle.jakarta.dwr;

import org.directwebremoting.ConversionException;
import org.directwebremoting.extend.*;
import org.directwebremoting.util.JavascriptUtil;
import org.directwebremoting.util.LocalUtil;

public class PrimitiveConverter extends AbstractConverter {

  public PrimitiveConverter() {
  }

  public Object convertInbound(Class<?> paramType, InboundVariable data) throws ConversionException {
    if (data.isNull()) {
      return null;
    } else {
      String value = data.getValue().trim();

      try {
        return LocalUtil.simpleConvert(value, paramType);
      } catch (NumberFormatException var5) {
        throw new ConversionException(paramType, "Format error converting number.");
      } catch (IllegalArgumentException var6) {
        throw new ConversionException(paramType);
      }
    }
  }

  public OutboundVariable convertOutbound(Object data, OutboundContext outctx) {
    Class<?> paramType = data.getClass();
    if (data.equals(Boolean.TRUE)) {
      return new NonNestedOutboundVariable("true");
    } else if (data.equals(Boolean.FALSE)) {
      return new NonNestedOutboundVariable("false");
    } else if (data instanceof Long) {
      return new NonNestedOutboundVariable('"' + JavascriptUtil.escapeJavaScript(data.toString()) + '"');
    } else {
      return paramType == Character.class ? new NonNestedOutboundVariable('"' + JavascriptUtil.escapeJavaScript(data.toString()) + '"') : new NonNestedOutboundVariable(data.toString());
    }
  }
}

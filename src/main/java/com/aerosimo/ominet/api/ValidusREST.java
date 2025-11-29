/******************************************************************************
 * This piece of work is to enhance validus project functionality.            *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      ValidusREST.java                                                *
 * Created:   28/11/2025, 22:42                                               *
 * Modified:  28/11/2025, 22:42                                               *
 *                                                                            *
 * Copyright (c)  2025.  Aerosimo Ltd                                         *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,            *
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES            *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND                   *
 * NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT                 *
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,               *
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING               *
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE                 *
 * OR OTHER DEALINGS IN THE SOFTWARE.                                         *
 *                                                                            *
 ******************************************************************************/

package com.aerosimo.ominet.api;

import com.aerosimo.ominet.core.model.AuthLayer;
import com.aerosimo.ominet.dao.impl.CardRequestDTO;
import com.aerosimo.ominet.dao.impl.CardResponseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/cardnumber")
public class ValidusREST {

    private static final Logger log = LogManager.getLogger(ValidusREST.class);

    @POST
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validatecard(CardRequestDTO req) {
        CardResponseDTO dto = AuthLayer.validate(req.getCardNumber());
        log.info("Validated card {} → valid: {}, network: {}, message: {} ", req.getCardNumber(),dto.getValid(),dto.getCardType(),dto.getMessage());
        return Response.ok(new CardResponseDTO(dto.getValid(),dto.getCardType(),dto.getMessage())).build();
    }
}
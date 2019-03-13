<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Something</title>
            </head>
            <body>
                <xsl:for-each select="people/husband">
                    <xsl:sort select="name"/>
                    <xsl:apply-templates select="name"/>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="name">
        <xsl:value-of select="."/>
        <br/>
    </xsl:template>

</xsl:stylesheet>
<!--- a little shitty way to process xmls, but we are glad that XSLT even exist -->
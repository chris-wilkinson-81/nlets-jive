<html>
    <head>
        <title>NLETS Agency Name Utility</title>
        <content tag="pagetitle">NLETS Agency Name Utility</content>
        <content tag="pageID">nlets-agency-name-utility</content>
        <content tag="pagehelp">
            <h3>Box Auth2 Settings</h3>
            <p>Box Auth2 Page Settings</p>
        </content>
    </head>
    <body>        
        <link rel="stylesheet" type="text/css" href="/plugins/agency-name-utility/resources/styles/admin/agency-name-utility.css"></link>
        
        <#if success>
            <div id="jive-success-box" class="jive-success-box" aria-live="polite" aria-atomic="true">
                <span class="jive-icon-med jive-icon-check"></span> Updated user agencies successfully.
            </div>
        </#if>   
    
    	<p>Use the following form below to idenify users by their US State or Federal Location code, and assign then to a comma separated list of agencies.</p>
    
    	<form id="agency-name-utility-form" method="post" action="/admin/nlets-agency-name-utility.jspa">
    		<table>
    			<tbody>
    				<tr>
    					<td class="label">US State or Federal Location Code</td>
    					<td><input type="text" name="stateCode" value="${stateCode!""}" /></td>
    				</tr>
    				<tr>
    					<td class="label">Agency Names (comma separated)</td>
    					<td><input type="text" name="agencyNames" value="${agencyNames!""}" /></td>
    				</tr>
    				<tr class="form-submit">
    					<td colspan="2">
    						<input type="submit" />
    					</td>
    				</tr>
    			</tbody>
    		</table>
    	</form>
    
    </body>
</html>
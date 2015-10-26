
<div class="span9" id="content">

	<div class="row-fluid">
		<!-- block -->
		<div class="block">
			<div class="navbar navbar-inner block-header">
				<div class="muted pull-left">Lista de Modelos</div>
				<button class="button-grid"
					onclick="window.location.href='/modelo/novo'">Novo</button>
			</div>
			<div class="block-content collapse in">
				<div class="span12">
					<table cellpadding="0" cellspacing="0" border="0"
						class="table table-striped table-bordered" id="example">
						<thead>
							<tr>
								<th>Identificador</th>
								<th>Nome</th>
								<th>Descrição</th>
								<th>Link</th>
								
								<th>Editar</th>
								<th>Remover</th>
							</tr>
						</thead>
						<tbody class="odd gradeX">
							<c:forEach items="${modeloList}" var="modelo">
								<tr>
								<td>${modelo.id }</td>
									<td>${modelo.nome }</td>
									<td>${modelo.descricao }</td>
									<td>http://www.xy-inc.com.br/servico/${modelo.link}</td>
									
									<td><a href="<c:url value="/modelo/editar/${modelo.id}"/>">Editar</a></td>
									<td>
										<a href="<c:url value="/modelo/excluir/${modelo.id}"/>">Excluir</a>
										
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


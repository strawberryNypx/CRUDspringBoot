package br.com.fujideia.iesp.tecback.service;


import br.com.fujideia.iesp.tecback.model.dto.ProdutorDTO;
import br.com.fujideia.iesp.tecback.repository.ProdutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.fujideia.iesp.tecback.model.Produtor;


@Service
@RequiredArgsConstructor
public class ProdutorService {

    private final ProdutorRepository produtorRepository;

    public List<ProdutorDTO> listar() {
        return produtorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProdutorDTO> buscarPorId(Long id) {
        return produtorRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ProdutorDTO criarProdutor(ProdutorDTO produtorDTO) {
        Produtor produtor= convertToEntity(produtorDTO);
        return convertToDTO(produtorRepository.save(produtor));
    }

    private Produtor convertToEntity(ProdutorDTO produtorDTO) {
        Produtor produtor = new Produtor();
        produtor.setId(produtorDTO.getId());
        produtor.setNome(produtorDTO.getNome());
        return produtor;
    }

    public Optional<ProdutorDTO> atualizarProdutor(Long id, ProdutorDTO produtorDTO) {
        return produtorRepository.findById(id).map(produtor -> {
            produtor.setNome(produtorDTO.getNome());

            return convertToDTO(produtorRepository.save(produtor));
        });
    }

    public boolean deletarProdutor(Long id) {
        if (produtorRepository.existsById(id)) {
            produtorRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private ProdutorDTO convertToDTO(Produtor produtor) {
        return new ProdutorDTO(produtor.getId(), produtor.getNome());
    }
}
